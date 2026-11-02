package grupo01.lostonyetes.gamelogic;

import java.util.List;

import grupo01.lostonyetes.engine.IEngine;
import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IState;
import grupo01.lostonyetes.engine.InputEvent;
import grupo01.lostonyetes.engine.InputType;

public class ChangeSceneButton extends Button implements GameObject {

    private IEngine eng;
    private IState scene;
    public ChangeSceneButton(int _x, int _y, int _w, int _h,float _arc, int _colorBackground, int _colorTexto, IFont _miFuente, String _texto, IEngine _eng, IState _sc) {
        super(_x, _y, _w, _h,_arc, _colorBackground, _colorTexto, _miFuente, _texto);
        this.eng = _eng;
        this.scene = _sc;
    }

    public void setSceneIDToChange(IState sc){
        this.scene = sc;
    }

    @Override
    public void init() {

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void render(IGraphics graphics) {

        graphics.setColor(0xFF000000);
        super.render(graphics);

    }

    @Override
    public void handleInput(List<InputEvent> events) {
        for(InputEvent ev : events){
            if(ev.getEventType() == InputType.ONRELEASE && checkIfPressed((int)ev.getPosX(),(int)ev.getPosY())){
                this.eng.setScene(this.scene);
            }
        }
    }
}
