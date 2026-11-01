package grupo01.lostonyetes.engine;

import grupo01.lostonyetes.engine.InputType;

public class InputEvent{

    float x, y;
    int id;
    InputType type;

    public InputEvent(float _x, float _y, InputType _type, int id){
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


    public float getPosX() {
        return x;
    }


    public float getPosY() {
        return y;
    }
}