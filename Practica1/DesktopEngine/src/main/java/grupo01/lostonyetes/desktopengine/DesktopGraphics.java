package grupo01.lostonyetes.desktopengine;

import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IImage;

public class DesktopGraphics implements IGraphics {
    public DesktopGraphics(){

    }
    @Override
    public IImage newImage(int _width, int _height){
        return new DesktopImage(_width, _height);
    }
    @Override
    public IFont newFont(int _size, boolean _bold, boolean _italic){
        return new DesktopFont(_size, _bold,_italic);
    }
    @Override
    public void setResolution(){
        //TO DO
    }
    @Override
    public void setColor(){
        //TO DO
    }
    @Override
    public void drawImage(){
        //TO DO
    }
    @Override
    public void fillRoundRectangle(){
        //TO DO
    }
    @Override
    public void fillRectangle(){
        //TO DO
    }
    @Override
    public void fillCircle(){
        //TO DO
    }
    @Override
    public void drawLine(){
        //TO DO
    }
    @Override
    public void drawTest(){
        //TO DO
    }
}