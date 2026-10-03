package grupo01.lostonyetes.androidengine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IImage;

public class AGraphics implements IGraphics, SurfaceHolder.Callback{

    private final SurfaceView surfaceView;
    private final SurfaceHolder holder;
    private Canvas canvas; // Se usará internamente en tus métodos de dibujo

    // Objeto Paint nativo de Android para gestionar colores y estilos
    private final Paint paint;
    private boolean surfaceReady = false;

    private int width = 0;
    private int height = 0;

    /**
     * motor debe recibir el contexto de la actividad
     * @param context la Activity que inicializa el juego
     */
    public AGraphics(Context context) {
        //Instanciamos el SurfaceView pasándole el contexto de la aplicación
        this.surfaceView = new SurfaceView(context);
        this.holder = this.surfaceView.getHolder();

        //Decirle al holder que esta clase escuchará el ciclo de vida
        this.holder.addCallback(this);

        // Inicializamos el objeto Paint
        this.paint = new Paint();
        // Configuramos el estilo por defecto a RELLENO (Fill)
        this.paint.setStyle(Paint.Style.FILL);
    }

    /**
     * Devuelve la vista para que la Activity principal del motor
     * pueda meterla en su setContentView().
     */
    public SurfaceView getView() {
        return this.surfaceView;
    }

    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }

    public boolean isSurfaceReady() {
        return surfaceReady;
    }

    /**
     * Prepara el canvas para poder ser utilizado
     */
    public void prepareCanvas() {
        if (surfaceReady && holder.getSurface().isValid()) {
            canvas = holder.lockCanvas();
        } else {
            canvas = null;
        }
    }

    /**
     * Mostramos por pantalla lo dibujado en el canvas
     */
    public void postCanvas() {
        if (canvas != null && holder.getSurface().isValid()) {
            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public IImage newImage(int _width, int _height) {
        return null;
    }

    @Override
    public IFont newFont(int _size, boolean _bold, boolean _italic) {
        return null;
    }

    @Override
    public void setResolution() {

    }

    /**
     * Metodo para setear el color del pincel
     * @param colorARGB int con el color a mostrar
     */
    @Override
    public void setColor(int colorARGB) {
        this.paint.setColor(colorARGB);
    }

    @Override
    public void drawImage() {

    }

    @Override
    public void fillRoundRectangle() {

    }

    /**
     * Metodo para pintar un rectangulo por pantalla
     * @param x posX
     * @param y posY
     * @param w widht
     * @param h height
     */
    @Override
    public void fillRectangle(int x, int y, int w, int h) {
        // Validamos que el canvas esté listo para dibujar en este frame
        if (canvas != null) {
            // Android dibuja rectángulos usando (izquierda, arriba, derecha, abajo)
            // Por lo tanto: derecha = x + w, abajo = y + h
            canvas.drawRect(x, y, x + w, y + h, this.paint);
        }
    }

    @Override
    public void fillCircle() {

    }

    @Override
    public void drawLine() {

    }

    @Override
    public void drawTest() {

    }

    /**
     * Metodo de surfaceHolder que se llama cada vez que la pantalla cambia de tamaño,
     * formato o se crea por primera vez
     * @param surfaceHolder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {
        this.surfaceReady = true;

        this.width = width;
        this.height = height;
    }

    /**
     * Metodo de surfaceHolder que se llama en el instante exacto en que la superficie de dibujo
     * se crea físicamente en la pantalla
     * @param surfaceHolder
     */
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        this.surfaceReady = true; // Por seguridad, nos aseguramos también aquí
    }

    /**
     * Metodo de surfaceHolder que se llama justo antes de que la superficie de dibujo sea destruida
     * @param surfaceHolder
     */
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        // La pantalla ya no está disponible. Apagamos la bandera inmediatamente.
        this.surfaceReady = false;
    }
}
