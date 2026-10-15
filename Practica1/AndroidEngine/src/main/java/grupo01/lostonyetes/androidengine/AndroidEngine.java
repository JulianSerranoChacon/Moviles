package grupo01.lostonyetes.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.IEngine;
import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IImage;
import grupo01.lostonyetes.engine.IState;

public class AndroidEngine implements IEngine, IState, Runnable {
    private final AssetManager assetManager;
    private final AGraphics graphics;
    private Thread gameThread;
    private volatile boolean running = false;


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
        return null;
    }

    @Override
    public ArrayList getInput() {
        return null;
    }

    @Override
    public void setState() {

    }

//    @Override
//    public void setLogicResolution(int w, int h) {
//        this.logicW = w;
//        this.logicH = h;
//    }

    /**
     * Metodo init llamado en la constructora para iniciar la ejecucion del motor y el hilo
     */
    @Override
    public void init() {
        if (running)
            return; // Si ya está corriendo, no hacemos nada

        running = true;
        gameThread = new Thread(this);
        gameThread.start(); // Esto dispara automáticamente el método run() en segundo plano
    }

    /**
     * Metodo update del motor
     * TODO: Quitar toda la basura del cuadrado
     */
    @Override
    public void update(double dt) {
        //muevo el cuadrado
        x += vx * dt;
        y += vy * dt;

        // pillo las dimensiones de pantalla
        int pantallaAncho = this.graphics.getWidth();
        int pantallaAlto = this.graphics.getHeight();

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

    /**
     * Bucle de renderizado del motor
     */
    @Override
    public void render() {
        //Intentamos preparar el lienzo
        this.graphics.prepareCanvas();

        // si falla el canvas salimos
        if (this.graphics.getView().getHolder().getSurface().isValid() == false) {
            return;
        }

        // limpiamos la ppantalla dibujando un fondo gris
        this.graphics.setColor(0xFF444444);
        this.graphics.fillRectangle(0, 0, 3000, 3000);

        // TODO: aqui estoy probando a dibujar un cuadrdado que habrá que borrar
        this.graphics.setColor(0xFFFF0000);
        this.graphics.fillRectangle(x, y, w, h);

        // TODO: Probamos a pintar una imagen
        IImage miImagen = this.graphics.newImage("CuboMinecraft.png", 50,50, w, h);
        this.graphics.drawImage(miImagen);

        // TODO: aqui estoy probando a cargar y dibujar textos, no es lo mejor en eficiencia, pero es por probar

        IFont miFuente = this.graphics.newFont("Roboto-Regular.ttf", 30f, false, false);

        this.graphics.drawText("¡Hola Android!", 50, 100, miFuente);
        // mandamos a renderizar
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

            // Informe de FPS
            double dt = (double) nanoElapsedTime / 1.0E9;
            this.update(dt);
            if (currentTime - prevTime > 1000000000l) {
                long fps = frames * 1000000000l / (currentTime - prevTime);
                System.out.println("" + fps + " fps");
                frames = 0;
                prevTime = currentTime;
            }
            ++frames;

            // Pintamos el frame
            this.render();
        }
    }
}
