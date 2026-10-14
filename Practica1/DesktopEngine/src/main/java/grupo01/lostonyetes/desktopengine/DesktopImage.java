package grupo01.lostonyetes.desktopengine;

import java.awt.image.BufferedImage;

import grupo01.lostonyetes.engine.IImage;

public class DesktopImage implements IImage {
    int width;
    int height;
    int posX;
    int posY;
    BufferedImage _buffImg;
    public DesktopImage(BufferedImage bIm, int x, int y, int w, int h){
        posX = x;
        posY = y;
        width = w;
        height = h;
        _buffImg = bIm;
    }
    @Override
    public int getWidth(){
        return width;
    }
    @Override
    public int getHeight(){
        return height;
    }
    @Override
    public int getPosX(){
        return posX;
    }
    @Override
    public int getPosY(){
        return posY;
    }
    public BufferedImage getBufferedImage(){
        return _buffImg;
    }
}
