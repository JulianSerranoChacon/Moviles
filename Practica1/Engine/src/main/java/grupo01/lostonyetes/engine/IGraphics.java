package grupo01.lostonyetes.engine;

public interface IGraphics {
    IImage newImage(int _width, int _height);
    IFont newFont(int _size, boolean _bold, boolean _italic);
    void setResolution();
    void setColor(int colorARGB);
    void drawImage();
    void fillRoundRectangle();
    void fillRectangle(int x, int y, int w, int h);
    void fillCircle();
    void drawLine();
    void drawTest();

}
