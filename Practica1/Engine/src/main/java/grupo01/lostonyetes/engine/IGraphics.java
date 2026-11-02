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
    public void setLogicSize(int x, int y);
    public int getLogicX(int x);
    public int getLogicY(int y);
    void setColor(int colorARGB);
    void drawText(String text, int x, int y, IFont font, boolean center);
    void drawImage(IImage img);
    void fillRoundRectangle(float x, float y, float w, float h, float arc);
    void fillRectangle(int x, int y, int w, int h);
    void fillCircle(int x, int y, int r);
    void drawLine();
    void drawTest();
    void clear();

}
