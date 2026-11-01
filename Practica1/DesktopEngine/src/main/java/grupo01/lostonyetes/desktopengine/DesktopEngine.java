package grupo01.lostonyetes.desktopengine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.IEngine;
import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IImage;
import grupo01.lostonyetes.engine.IState;
import grupo01.lostonyetes.engine.InputEvent;
import grupo01.lostonyetes.engine.SceneManager;

public class DesktopEngine implements IEngine, Runnable {
    DesktopGraphics mDesktopGraphics;
    DesktopAudio mDesktopAudio;
    SceneManager mSceneManager;
    DesktopInput mInputManager;
    private Thread gameThread;

//    private int logicW;
//    private int logicH;

    //TODO borrar, todo esto es del cuadrado de pruena
    int x = 10,y = 10,w = 150, h=150;
    int vx =500;
    int vy =500;

    private volatile boolean running = false;

    public DesktopEngine(){
        mDesktopGraphics = new DesktopGraphics();
        mDesktopAudio = new DesktopAudio();
        mSceneManager = new SceneManager();
        mInputManager = new DesktopInput(mDesktopGraphics.getMyView());
//        logicW = 0;
//        logicH = 0;

        init();
    }

    @Override
    public InputStream openInputStream(String filepath) {
        try {
            return new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public IGraphics getGraphics(){
        return mDesktopGraphics;
    }
    @Override
    public IAudio getAudio(){
        return mDesktopAudio;
    }
    @Override
    public ArrayList getInput(){
        return null;
    }
    @Override
    public void setState(){

    }

    @Override
    public void setScene(IState s) {
        this.mSceneManager.SetScene(s);
    }

//    @Override
//    public void setLogicResolution(int w, int h) {
//        this.logicW = w;
//        this.logicH = h;
//    }

    public void setWindowInfo(String title, int width, int height ){
        mDesktopGraphics.setWindowInfo(title,width,height);
    }
    public void init() {
        if (running)
            return; // Si ya está corriendo, no hacemos nada

        running = true;
        gameThread = new Thread(this);
        gameThread.start(); // Esto dispara automáticamente el método run() en segundo plano
        mSceneManager.init();
    }

    @Override
    public void run() {
        // Espera activa limpia hasta que la pantalla este lista
        while (this.running && !this.mDesktopGraphics.isRenderable()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        // Para limitar a 60 FPS
        final double NANO_PER_FRAME = 1000000000.0 / 60.0;
        long lastTime = System.nanoTime();
        long prevTime = lastTime; // Informes de FPS
        int frames = 0;

        // Bucle de juego principal
        while (running) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastTime;

            // Solo procesamos frame si ha transcurrido el tiempo limite (1/60 segundos)
            if (nanoElapsedTime >= NANO_PER_FRAME) {
                // Delta time expresado en segundos (ej. 0.0166 s)
                double dt = nanoElapsedTime / 1000000000.0;

                // Actualizamos lastTime solo cuando procesamos un frame
                lastTime = currentTime;

                List<InputEvent>  events = mInputManager.getEvents();
                mSceneManager.handleInput(events);

                //Actualizacion logica con dt en segundos
                mSceneManager.update(dt);
                /*
                // Informe de FPS
                frames++;
                if (currentTime - prevTime >= 1000000000L) {
                    long fps = (frames * 1000000000L) / (currentTime - prevTime);
                    System.out.println(fps + " fps");
                    frames = 0;
                    prevTime = currentTime;
                }
                */

                //Renderizado
                if (this.mDesktopGraphics.prepareFrame()) {
                    try {
                        this.mSceneManager.render();
                    } finally {
                        this.mDesktopGraphics.endFrame();
                    }
                }

                mSceneManager.ChangeIfNextScene();
            }
        }
    }
}