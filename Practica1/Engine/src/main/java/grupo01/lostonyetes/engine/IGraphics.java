package grupo01.lostonyetes.engine;

public interface IGraphics {
    IImage newImage(int _width, int _height);
    IFont newFont(int _size, boolean _bold, boolean _italic);
    void setResolution();
    void setColor();
    void drawImage();
    void fillRoundRectangle();
    void fillRectangle();
    void fillCircle();
    void drawLine();
    void drawTest();

}
