package grupo01.lostonyetes.desktopengine;

import java.awt.Graphics;
import java.awt.Graphics2D;
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

public class DesktopGraphics implements IGraphics{

    private JFrame myView;
    private BufferStrategy bufferStrategy;
    private Graphics graphics2D;

    private float scale;
    private int dx; //traslacion en X
    private int dy; //traslacion en Y
    private int logicW;
    private int logicH;
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

        this.logicW = width;
        this.logicH = height;
    }

    @Override
    public void scale(float sw, float sh) {
        ((Graphics2D) this.graphics2D).scale(sw, sh);
    }

    @Override
    public void translate(int dx, int dy) {
        this.graphics2D.translate(dx, dy);

    }

    @Override
    public void restore() {

    }

    @Override
    public void save() {

    }

//    @Override
//    public void setClip(int x, int y, int w, int h) {
//        this.graphics2D.setClip(Shape(x,y,w,h));
//    }

    private void calculateTransforms(){
        float tempW = this.myView.getWidth()/this.logicW;
        float tempH = this.myView.getHeight()/this.logicH;

        this.scale = Math.min(tempW,tempH); // tempW < tempH ? tempW : tempH;

        float tW = this.logicW * this.scale;
        float tH = this.logicH * this.scale;

        this.dx = (int)((this.myView.getWidth() - tW)/2);
        this.dy = (int)((this.myView.getHeight() - tH)/2);
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
            Graphics graphics = this.bufferStrategy.getDrawGraphics();

            if (graphics != null) {
                graphics.setFont(desktopFont.getAwtFont());

                setColor(0xFFFFFFFF); //ESTO HAY QUE CAMBIARLO, ESTA PARA PROBAR QUE SE VEA

                graphics.drawString(text, x, y);
                graphics.dispose();
            }
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
    @Override
    public void fillRectangle(int x, int y, int w, int h){
        if (this.graphics2D != null) {
            this.graphics2D.fillRect(x, y, w, h);
        }
    }
    @Override
    public void fillCircle(){
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
        /*
        //cosas del scale
        calculateTransforms();
        this.scale(this.scale, this.scale);
        this.translate(this.dx, this.dy);
        //cosas del scale
        */
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

    public void drawCircle(int cx, int cy, int radius) {
        this.graphics2D.drawOval(cx, cy, 2*radius, 2*radius);
    }
}