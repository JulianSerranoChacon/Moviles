package grupo01.lostonyetes.engine;

public interface IGraphics {
    IImage newImage();
    IFont newFont();
    void setResolution();
    void setColor();
    void drawImage();
    void fillRoundRectangle();
    void fillRectangle();
    void fillCircle();
    void drawLine();
    void drawTest();

}
