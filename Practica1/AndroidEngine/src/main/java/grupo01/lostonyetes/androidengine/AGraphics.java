package grupo01.lostonyetes.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;

import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IImage;
import grupo01.lostonyetes.engine.ScalableGraphics;

public class AGraphics extends ScalableGraphics implements SurfaceHolder.Callback{
    private final AssetManager assetManager;
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
        this.assetManager = context.getAssets();
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

    @Override
    public int getWidth() { return this.width; }

    @Override
    public int getHeight() { return this.height; }

    public boolean isSurfaceReady() {
        return surfaceReady;
    }

    /**
     * Prepara el canvas para poder ser utilizado
     */
    public boolean prepareCanvas() {
        if (surfaceReady && holder.getSurface().isValid()) {
            canvas = holder.lockCanvas();
        } else {
            canvas = null;
        }
        AspectRatio();
        return canvas == null;
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
    public IImage newImage(String filePath, int x, int y, int w, int h) {
        return new AImage(this.assetManager, filePath, x, y, w, h);
    }

    @Override
    public IFont newFont(String filePath, float _size, boolean _bold, boolean _italic) {
        return new AFont(this.assetManager, filePath, _size);
    }

    @Override
    public void setResolution( int width, int height) {

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
    public void drawText(String text, int x, int y, IFont font) {
        if (font != null && this.canvas != null) {
            AFont androidFont = (AFont) font; //Convertimos IFont en AFont y creamos un nuevo AFont

            this.paint.setTypeface(androidFont.getTypeface());
            this.paint.setTextSize(androidFont.getSize());

            this.canvas.drawText(text, x, y, paint); // Dibuja el texto en pantalla
        }
    }
    @Override
    public void drawImage(IImage img) {
        if (img != null && this.canvas != null){
            AImage androidImage = (AImage) img;
            if (androidImage.getBitmap() != null) {
                Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
                Rect pos = new Rect(img.getPosX(), img.getPosY(),
                        img.getPosX() + img.getWidth(), img.getPosY() + img.getHeight());
                canvas.drawBitmap(androidImage.getBitmap(), null, pos, paint);
            }
        }
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
    /**
     * Metodo para pintar un circulo por pantalla
     * @param x posCenterX
     * @param y posCenterY
     * @param r radius
     */
    @Override
    public void fillCircle(int x, int y, int r) {
        // Validamos que el canvas esté listo para dibujar en este frame
        if (canvas != null) {
            // Android dibuja circulos usando (centro en x, centro en y, radio)
            canvas.drawCircle(x, y, r, this.paint);
        }
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

    @Override
    public int GetRealWidth() {
        return surfaceView.getWidth();
    }

    @Override
    public int GetRealHeight() {
        return surfaceView.getHeight();
    }

    @Override
    protected void scale(float x, float y) {
        if(canvas != null)canvas.scale(x,y);
    }

    @Override
    protected void translate(float x, float y) {
        if(canvas != null)canvas.translate(x, y);
    }
    @Override
    protected void AspectRatio(){
        scale = Math.min((float) GetRealWidth() / logicWidth, (float) GetRealHeight() / logicHeight);

        offsetX = ((float) GetRealWidth() - (logicWidth * scale)) / 2f;
        offsetY = ((float) GetRealHeight() - (logicHeight * scale)) / 2f;

        translate(offsetX, offsetY);
        scale(scale, scale);
    }
}
