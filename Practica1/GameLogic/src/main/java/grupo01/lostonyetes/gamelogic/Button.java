package grupo01.lostonyetes.gamelogic;

import java.util.List;

import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.InputEvent;

public class Button{
    int x,y;
    int w,h;
    int colorBackground;
    int colorTexto;
    IFont miFuente;
    String texto;
    float arc;

    public Button(int _x, int _y, int _w, int _h, float _arc,
                  int _colorBackground, int _colorTexto, IFont _miFuente, String _texto){
        x = _x; y = _y;
        w = _w; h = _h;
        colorBackground = _colorBackground;
        colorTexto = _colorTexto;
        miFuente = _miFuente;
        texto = _texto;
        this.arc = _arc;
    }
    public void SetText(String s){
        texto = s;
    }
    public void SetPos(int _x, int _y){
        x = _x;
        y = _y;
    }
    public void SetSize(int _w, int _h){
        w = _w;
        h = _h;
    }
    public void SetFuente(IFont font){
        miFuente = font;
    }

    public void render(IGraphics graphics){
        graphics.setColor(colorBackground);
        graphics.fillRoundRectangle(x,y,w,h,arc);
        graphics.setColor(colorTexto);
        graphics.drawText(texto, x + (w/2), y + (h/2), miFuente, true);
    }

    public boolean checkIfPressed(int _x, int _y) {
        return (_x >= this.x && _x < this.x + this.w) &&
                (_y >= this.y && _y < this.y + this.h);
    }
}
