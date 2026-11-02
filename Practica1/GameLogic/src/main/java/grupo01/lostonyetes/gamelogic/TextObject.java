package grupo01.lostonyetes.gamelogic;

import java.awt.Font;
import java.util.List;

import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.InputEvent;

public class TextObject implements GameObject{
    IFont mFuente;
    String mtext;
    int x,y;
    TextObject(IFont font, String text, int _x, int _y){
        this.mFuente = font;
        this.mtext = text;
        this.x = _x;
        this.y = _y;
    }
    @Override
    public void init() {

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void render(IGraphics graphics) {
        graphics.drawText(this.mtext, x, y, this.mFuente, true);
    }

    @Override
    public void handleInput(List<InputEvent> events) {

    }
}
