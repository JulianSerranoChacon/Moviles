package grupo01.lostonyetes.engine;

public interface IGraphics {
    IImage newImage(String path, int x, int y, int w, int h);
    IFont newFont(String filePath, float _size, boolean _bold, boolean _italic);
    void setResolution( int width, int height);
//    public void scale(float sw, float sh);
//    public void translate(int dx, int dy);
//    public void restore(); //restaurar transformaciones en canvas
//    public void save(); //guardar transformaciones en canvas
//    //public void setClip(int x, int y, int w, int h);
    public int getWidth();
    public int getHeight();
    void setColor(int colorARGB);
    void drawText(String text, int x, int y, IFont font);
    void drawImage(IImage img);
    void fillRoundRectangle();
    void fillRectangle(int x, int y, int w, int h);
    void fillCircle();
    void drawLine();
    void drawTest();

}
