package grupo01.lostonyetes.desktopengine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import javax.imageio.ImageIO;

import javax.swing.JFrame;

import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IImage;
import grupo01.lostonyetes.engine.ScalableGraphics;

public class DesktopGraphics extends ScalableGraphics {

    private JFrame myView;
    private BufferStrategy bufferStrategy;
    private Graphics2D graphics2D;
    private final Map<String, BufferedImage> imagenes = new HashMap<>();

    public DesktopGraphics(){
        this.myView = new JFrame();

        this.myView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.myView.setSize(800, 600);
        this.myView.setVisible(true);

        // Intentamos crear el buffer strategy con 2 buffers.
        int intentos = 100;
        while(intentos-- > 0) {
            try {
                this.myView.createBufferStrategy(2);
                this.bufferStrategy = this.myView.getBufferStrategy();
                break;
            }
            catch(Exception e) {
            }
        } // while pidiendo la creación de la buffeStrategy
        if (intentos == 0) {
            System.err.println("No pude crear la BufferStrategy");
            return;
        }
        else {
            // En "modo debug" podríamos querer escribir esto.
            //System.out.println("BufferStrategy tras " + (100 - intentos) + " intentos.");
        }
        //carga de imagenes de data
        File carpeta = new File("data/images/");
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File a : archivos){
                    String extension = a.getName().substring(a.getName().lastIndexOf('.') + 1).toLowerCase();

                    if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")){

                        try {
                            BufferedImage imagen = ImageIO.read(a);
                            if (imagen != null) {
                                imagenes.put(a.getName(), imagen);
                            }

                        } catch (IOException e) {
                            System.err.println("Error al cargar " + a.getName() + ": " + e.getMessage());
                        }
                    }
                }
            }
        }
    }

    public JFrame getMyView() {
        return myView;
    }

    public void setWindowInfo(String title, int width, int height ){
        this.myView.setTitle(title);
        setResolution(width,height);
    }

    public boolean isRenderable(){
        return this.myView.isVisible() &&
                this.myView.getWidth() != 0 &&
                this.bufferStrategy != null;
    }

    @Override
    public IImage newImage(String filePath, int x, int y, int w, int h){
        return new DesktopImage(imagenes.get(filePath),x, y,w,h);
    }
    @Override
    public IFont newFont(String filePath, float _size, boolean _bold, boolean _italic) {
        try {
            InputStream is = new FileInputStream(filePath);
            return new DesktopFont(is, _size, _bold, _italic);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void setResolution( int width, int height){
        // Garantiza que la zona de dibujo mida lo indicado
        this.myView.setSize(width,height);
        this.myView.setLocationRelativeTo(null); // Centra la ventana en pantalla
    }

    @Override
    public int getWidth() {
        // Devuelve el ancho útil del canvas de dibujo, no de la ventana entera
        return this.myView.getWidth();
    }

    @Override
    public int getHeight() {
        // Devuelve el ancho útil del canvas de dibujo, no de la ventana entera
        return this.myView.getHeight();
    }

    @Override
    public void setColor(int colorARGB){
        if (this.graphics2D != null) {
            // El parámetro 'true' indica que el entero incluye canal Alpha (transparencia)
            this.graphics2D.setColor(new java.awt.Color(colorARGB, true));
        }
    }
    @Override
    public void drawText(String text, int x, int y, IFont font) {
        if (font != null && this.bufferStrategy != null) {
            DesktopFont desktopFont = (DesktopFont) font;
            graphics2D.setFont(desktopFont.getAwtFont());

            graphics2D.drawString(text, x, y);
        }
    }
    @Override
    public void drawImage(IImage img){
        DesktopImage Img = (DesktopImage) img;
        if (Img.getBufferedImage() != null) {
            graphics2D.drawImage(
                    Img.getBufferedImage(),
                    Img.getPosX(), Img.getPosY(),
                    Img.getWidth(), Img.getHeight(),
                    null
            );
        }
    }
    @Override
    public void fillRoundRectangle(){
        //TODO
    }

    /**
     * Metodo para pintar un rectangulo por pantalla
     * @param x posX
     * @param y posY
     * @param w widht
     * @param h height
     */
    @Override
    public void fillRectangle(int x, int y, int w, int h){
        if (this.graphics2D != null) {
            this.graphics2D.fillRect(x, y, w, h);
        }
    }
    
    /**
     * Metodo para pintar un circulo por pantalla
     * @param x posCenterX
     * @param y posCenterY
     * @param r radius
     */
    @Override
    public void fillCircle(int x, int y, int r){
        if (this.graphics2D != null) {
            // La posicion que pasamos en x e y es la de la del centro del circulo,
            // pero fillOval piensa que es la esquina superior izquierda,
            // si queremos poder centrar el circulo en el centro debemos de restar x e y con el radio r
            this.graphics2D.fillOval(x-r, y-r, 2*r, 2*r);
        }
    }
    @Override
    public void drawLine(){
        //TODO
    }
    @Override
    public void drawTest(){
        //TODO
    }

    public boolean prepareFrame() {
        if (!isRenderable()) {
            return false;
        }
        // Obtenemos el contexto gráfico para dibujar en este frame
        this.graphics2D = (Graphics2D) this.bufferStrategy.getDrawGraphics();

        AspectRatio();
        return this.graphics2D != null;
    }

    public boolean endFrame() {
        this.graphics2D.dispose();
        this.graphics2D = null;
        if (this.bufferStrategy.contentsRestored()) {
            return false;
        }
        this.bufferStrategy.show();
        return !this.bufferStrategy.contentsLost();
    }

    @Override
    public int GetRealWidth() {
        return myView.getWidth();
    }

    @Override
    public int GetRealHeight() {
        return myView.getHeight();
    }

    @Override
    protected void scale(float x, float y) {
        this.graphics2D.scale(x,y);
    }

    @Override
    protected void translate(float x, float y) {
        this.graphics2D.translate(x,y);
    }
    @Override
    protected void AspectRatio(){
        Insets insets = myView.getInsets();

        float tempX = ((float) GetRealWidth() - insets.left - insets.right) / logicWidth;
        float tempY = ((float) GetRealHeight() - insets.top - insets.bottom) / logicHeight;

        scale = Math.min(tempX, tempY);

        offsetX = insets.left + ((((float) GetRealWidth() - insets.left - insets.right) - (logicWidth * scale)) / 2f);
        offsetY = insets.top + ((((float) GetRealHeight() - insets.top - insets.bottom) - (logicHeight * scale)) / 2f);

        translate(offsetX, offsetY);
        scale(scale, scale);
    }
}