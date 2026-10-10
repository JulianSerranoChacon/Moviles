package grupo01.lostonyetes.androidengine;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import grupo01.lostonyetes.engine.IFont;

public class AFont implements IFont {
    private Typeface typeface;
    private float size;

    public AFont(AssetManager assetManager, String filePath, float size) {
        this.typeface = Typeface.createFromAsset(assetManager, filePath); //carga la fuente
        this.size = size;
    }

    public Typeface getTypeface() {
        return this.typeface;
    }
    @Override
    public float getSize() {
        return this.size;
    }

    @Override
    public boolean isBold() {
        return false;
    }

    @Override
    public boolean isItalic() {
        return false;
    }
}
