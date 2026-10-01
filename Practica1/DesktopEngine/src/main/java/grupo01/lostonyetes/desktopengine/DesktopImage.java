package grupo01.lostonyetes.desktopengine;

import grupo01.lostonyetes.engine.IImage;

public class DesktopImage implements IImage {
    int width;
    int height;
    public DesktopImage(int _width, int _heigth){
        width = _width;
        height = _heigth;
    }
    @Override
    public int getWidth(){
        return width;
    }
    @Override
    public int getHeight(){
        return height;
    }
}
