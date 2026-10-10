package grupo01.lostonyetes.desktopengine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.IEngine;
import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IState;

public class DesktopEngine implements IEngine, IState,Runnable {
    DesktopGraphics mDesktopGraphics;
    DesktopAudio mDesktopAudio;
    private Thread gameThread;

    //TODO borrar, todo esto es del cuadrado de pruena
    int x = 10,y = 10,w = 150, h=150;
    int vx =500;
    int vy =500;

    private volatile boolean running = false;

    public DesktopEngine(){
        mDesktopGraphics = new DesktopGraphics();
        mDesktopAudio = new DesktopAudio();

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
    public void setWindowInfo(String title, int width, int height ){
        mDesktopGraphics.setWindowInfo(title,width,height);
    }
    @Override
    public void init() {
        if (running)
            return; // Si ya está corriendo, no hacemos nada

        running = true;
        gameThread = new Thread(this);
        gameThread.start(); // Esto dispara automáticamente el método run() en segundo plano

    }
    @Override
    public void update(double dt) {
        //muevo el cuadrado
        x += vx * dt;
        y += vy * dt;

        // pillo las dimensiones de pantalla
        int pantallaAncho = this.mDesktopGraphics.getWidth();
        int pantallaAlto = this.mDesktopGraphics.getHeight();

        // Si no me llegan las dimensiones me cago encima
        if (pantallaAncho == 0 || pantallaAlto == 0) return;

        //rebote eje x
        if (x < 0) {
            x = 0;
            vx = Math.abs(vx); // Fuerza a que la velocidad sea positiva (va a la derecha)
        }
        else if ((x + w) >= pantallaAncho) {
            x = pantallaAncho - w -2; // Lo separamos 2 píxeles de la pared para evitar bucles
            vx = -Math.abs(vx); // Fuerza a que la velocidad sea negativa (va a la izquierda de golpe)
        }

        // rebote eje y
        if (y < 0) {
            y = 0;
            vy = Math.abs(vy); // Fuerza a que la velocidad sea positiva (va hacia abajo)
        }
        else if ((y + h) >= pantallaAlto) {
            y = pantallaAlto - h - 2; // Lo separamos 2 píxeles del suelo
            vy = -Math.abs(vy); // Fuerza a que la velocidad sea negativa (va hacia arriba)
        }
    }
    @Override
    public void render() {
        //Limpiar pantalla o pintar fondo con un rectángulo azul
        this.mDesktopGraphics.setColor(0xFF0000FF); // Alpha: FF, Rojo: 00, Verde: 00, Azul: FF
        this.mDesktopGraphics.fillRectangle(0, 0, 10000,10000);

        //Pintar un rectangulo verde
        this.mDesktopGraphics.setColor(0xFF00FF00); // Verde opaco
        this.mDesktopGraphics.fillRectangle(x, y, w, h);

        //Cargamos una fuente de texto y escribimos texto en pantalla
        IFont miFuente = this.mDesktopGraphics.newFont("Roboto-Regular.ttf", 30f, false, false);

        this.mDesktopGraphics.drawText("¡Hola Escritorio!", 50, 100, miFuente);
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

                //Actualizacion logica con dt en segundos
                this.update(dt);

                // Informe de FPS
                frames++;
                if (currentTime - prevTime >= 1000000000L) {
                    long fps = (frames * 1000000000L) / (currentTime - prevTime);
                    System.out.println(fps + " fps");
                    frames = 0;
                    prevTime = currentTime;
                }

                //Renderizado
                if (this.mDesktopGraphics.prepareFrame()) {
                    try {
                        this.render();
                    } finally {
                        this.mDesktopGraphics.endFrame();
                    }
                }
            }
        }
    }
}