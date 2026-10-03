package grupo01.lostonyetes.androidengine;

import android.content.Context;
import android.view.SurfaceView;

import java.util.ArrayList;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.IEngine;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IState;

public class AndroidEngine implements IEngine, IState, Runnable {

    private final AGraphics graphics;
    private Thread gameThread;
    private volatile boolean running = false;

    //TODO borrar, todo esto es del cuadrado de pruena
    int x = 10,y = 10,w = 300, h=300;
    int vx =10;
    int vy =10;


    /**
     * motor debe recibir el contexto de la actividad
     * @param context la actividad que inicializa el juego
     */
    public AndroidEngine(Context context){
        this.graphics = new AGraphics(context);

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
    public void update() {
        //muevo el cuadrado
        x += vx;
        y += vy;

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

        // mandamos a renderizar
        this.graphics.postCanvas();
    }


    /**
     * Metodo run del hilo donde está el bucle principal
     */
    @Override
    public void run() {
        //Para ir a 60fps
        final double NANO_PER_FRAME = 1000000000.0 / 60.0;
        long lastTime = System.nanoTime();

        while (running) {
            // Si el hilo de ejecución arranca antes de que la SurfaceView
            // esté lista, le damos un respiro al bucle para que no intente
            // calcular lógica ni renderizados sobre la nada.
            if (!this.graphics.isSurfaceReady()) {
                try {
                    Thread.sleep(10); // Espera 10 milisegundos y vuelve a comprobar
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lastTime = System.nanoTime(); // Reseteamos el tiempo para evitar saltos locos de frames
                continue; // Saltamos al siguiente ciclo del while hasta que la pantalla exista
            }

            long now = System.nanoTime();
            double deltaTime = (now - lastTime) / NANO_PER_FRAME;

            if (deltaTime >= 1) {
                update(); //Actualiza logicas y fisicas
                render(); //Dibuja el frame en pantalla
                lastTime = now;
            }

            // Un pequeño respiro al procesador
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
