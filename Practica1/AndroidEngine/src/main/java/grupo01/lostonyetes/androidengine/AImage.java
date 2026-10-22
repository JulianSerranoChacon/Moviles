package grupo01.lostonyetes.androidengine;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;

import grupo01.lostonyetes.engine.IImage;

public class AImage implements IImage {
    Bitmap _bitmap;
    int width;
    int height;
    int posX;
    int posY;
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    public Bitmap getBitmap(){
        return _bitmap;
    }
    public AImage(AssetManager assetManager, String filePath, int x, int y, int w, int h){
        width = w;
        height = h;
        posX = x;
        posY = y;
        try{
            InputStream is = assetManager.open("images/"+filePath);
            _bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            System.err.println("Error al cargar " + filePath);
            e.printStackTrace();
        }
    }
}
