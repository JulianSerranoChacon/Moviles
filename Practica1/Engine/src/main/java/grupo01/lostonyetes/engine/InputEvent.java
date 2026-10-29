package grupo01.lostonyetes.engine;

import grupo01.lostonyetes.engine.InputType;

public class InputEvent{

    int x, y;
    int id;
    InputType type;

    public InputEvent(int _x, int _y, InputType _type, int id){
        x = _x;
        y = _y;
        type = _type;
    }

    public InputType getEventType() {
        return type;
    }

    public int getEventID() {
        return 0;
    }


    public int getPosX() {
        return x;
    }


    public int getPosY() {
        return y;
    }
}