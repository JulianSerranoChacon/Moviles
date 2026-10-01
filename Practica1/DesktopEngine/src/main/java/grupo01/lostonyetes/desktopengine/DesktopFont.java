package grupo01.lostonyetes.desktopengine;

import grupo01.lostonyetes.engine.IFont;

public class DesktopFont implements IFont {
    int size;
    boolean bold;
    boolean italic;
    public DesktopFont(int _size, boolean _bold, boolean _italic){
        size = _size;
        bold = _bold;
        italic = _italic;
    }
    @Override
    public int getSize(){
        return size;
    }
    @Override
    public boolean isBold(){
        return bold;
    }
    @Override
    public boolean isItalic(){
        return italic;
    }
}
