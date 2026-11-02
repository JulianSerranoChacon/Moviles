package grupo01.lostonyetes.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.SurfaceView;

import java.io.IOException;
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

public class AndroidEngine implements IEngine, Runnable {
    private final AssetManager assetManager;
    private final AGraphics graphics;
    private final AAudio audioSystem;
    private final AInput inputSystem;
    private Thread gameThread;
    private volatile boolean running = false;
    private SceneManager mSceneManager;

//    private int logicW;
//    private int logicH;


    //TODO borrar, todo esto es del cuadrado de pruena
    int x = 10,y = 10,w = 300, h=300;
    int vx =1000;
    int vy =1000;


    /**
     * motor debe recibir el contexto de la actividad
     * @param context la actividad que inicializa el juego
     */
    public AndroidEngine(Context context){
        this.assetManager = context.getAssets();
        this.graphics = new AGraphics(context);
        this.audioSystem = new AAudio(context);
        this.mSceneManager = new SceneManager();
        this.inputSystem = new AInput(this.graphics.getView(),this);

        System.out.println("A");
        // Arrancamos el motor e inicializamos el hilo inmediatamente
        init();
    }

    /**
     * Metodo publico para que el modulo del juego extraiga la vista
     * y pueda hacer el setContentView().
     */
    public SurfaceView getLayoutView() {
        return this.graphics.getView();
    }

    /**
     * Metodo publico que devuelve el modulo de graficos del motor
     * @return
     */

    @Override
    public InputStream openInputStream(String filepath) {
        try {
            return this.assetManager.open(filepath);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public IGraphics getGraphics() {
        return graphics;
    }

    @Override
    public IAudio getAudio() {
        return audioSystem;
    }

    @Override
    public ArrayList getInput() {
        return null;
    }

    @Override
    public void setState() {

    }

    @Override
    public void setScene(IState s) {
        mSceneManager.SetScene(s);
    }

//    @Override
//    public void setLogicResolution(int w, int h) {
//        this.logicW = w;
//        this.logicH = h;
//    }

    /**
     * Metodo init llamado en la constructora para iniciar la ejecucion del motor y el hilo
     */
    public void init() {
        if (running)
            return; // Si ya está corriendo, no hacemos nada

        running = true;
        gameThread = new Thread(this);
        gameThread.start(); // Esto dispara automáticamente el método run() en segundo plano
    }

    /**
     * Metodo update del motor
     */
    public void update(double dt) {
        mSceneManager.update(dt);
    }

    /**
     * Bucle de renderizado del motor
     */
    public void render() {
        this.graphics.prepareCanvas();
        mSceneManager.render();
        this.graphics.postCanvas();
    }


    /**
     * Metodo run del hilo donde está el bucle principal
     */
    @Override
    public void run() {
        if (gameThread != Thread.currentThread()) {
        // Evita que cualquiera que no sea esta clase llame a este Runnable en un Thread
        // Programación defensiva
        throw new RuntimeException("run() should not be called directly");
        }


        // Espera activa limpia hasta que la pantalla este lista
        while (this.running && !this.graphics.isSurfaceReady()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        long lastFrameTime = System.nanoTime();
        long prevTime = lastFrameTime; // Informes de FPS
        int frames = 0;

        // Bucle de juego principal
        while (running) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;

            double dt = (double) nanoElapsedTime / 1.0E9;

            List<InputEvent> events = inputSystem.getEvents();
            mSceneManager.handleInput(events);

            this.update(dt);
/*
            // Informe de FPS
            if (currentTime - prevTime > 1000000000l) {
                long fps = frames * 1000000000l / (currentTime - prevTime);
                System.out.println("" + fps + " fps");
                frames = 0;
                prevTime = currentTime;
            }
            ++frames;
*/
            // Pintamos el frame
            this.render();

            mSceneManager.ChangeIfNextScene();
        }
    }
}
