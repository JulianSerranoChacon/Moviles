package grupo01.lostonyetes.desktopengine;

import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IImage;

public class DesktopGraphics implements IGraphics {
    public DesktopGraphics(){

    }
    public IImage newImage(int _width, int _height){
        return new DesktopImage(_width, _height);
    }
    public IFont newFont(int _size, boolean _bold, boolean _italic){
        return new DesktopFont(_size, _bold,_italic);
    }
    public void setResolution(){
        //TO DO
    }
    public void setColor(){
        //TO DO
    }
    public void drawImage(){
        //TO DO
    }
    public void fillRoundRectangle(){
        //TO DO
    }
    public void fillRectangle(){
        //TO DO
    }
    public void fillCircle(){
        //TO DO
    }
    public void drawLine(){
        //TO DO
    }
    public void drawTest(){
        //TO DO
    }
}