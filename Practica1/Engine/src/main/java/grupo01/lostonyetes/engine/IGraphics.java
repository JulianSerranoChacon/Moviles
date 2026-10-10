package grupo01.lostonyetes.engine;

public interface IGraphics {
    IImage newImage(int _width, int _height);
    IFont newFont(String filePath, float _size, boolean _bold, boolean _italic);
    void setResolution( int width, int height);
    public int getWidth();
    public int getHeight();
    void setColor(int colorARGB);
    void drawText(String text, int x, int y, IFont font);
    void drawImage(String nombre, int X, int Y, int width, int height);
    void fillRoundRectangle();
    void fillRectangle(int x, int y, int w, int h);
    void fillCircle();
    void drawLine();
    void drawTest();

}
