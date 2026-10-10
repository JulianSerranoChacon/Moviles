package grupo01.lostonyetes.desktopengine;

import java.awt.Font;
import java.io.InputStream;

import grupo01.lostonyetes.engine.IFont;

public class DesktopFont implements IFont {
    private Font awtFont;
    private float size;
    private boolean bold;
    private boolean italic;
    public DesktopFont(InputStream is, float _size, boolean _bold, boolean _italic){
        try {
            this.awtFont = Font.createFont(Font.TRUETYPE_FONT, is); // Crea la fuente TrueType, quizas haya que parametrizar esto
            this.awtFont = this.awtFont.deriveFont(Font.PLAIN, _size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.size = _size;
        this.bold = _bold;
        this.italic = _italic;
    }

    public Font getAwtFont(){
        return this.awtFont;
    }
    @Override
    public float getSize(){
        return this.size;
    }
    @Override
    public boolean isBold(){
        return this.bold;
    }
    @Override
    public boolean isItalic(){
        return this.italic;
    }
}
