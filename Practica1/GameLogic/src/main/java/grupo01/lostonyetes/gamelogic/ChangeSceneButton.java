package grupo01.lostonyetes.gamelogic;

import grupo01.lostonyetes.engine.IEngine;
import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IState;

public class ChangeSceneButton extends Button {

    private IEngine eng;
    private IState scene;
    public ChangeSceneButton(int _x, int _y, int _w, int _h, int _colorBackground, int _colorTexto, IFont _miFuente, String _texto, IEngine _eng, IState _sc) {
        super(_x, _y, _w, _h, _colorBackground, _colorTexto, _miFuente, _texto);
        this.eng = _eng;
        this.scene = _sc;
    }

    public void setSceneIDToChange(IState sc){
        this.scene = sc;
    }

    @Override
    public void OnClick(){
        this.eng.setScene(this.scene);
    }
}
