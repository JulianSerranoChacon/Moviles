package grupo01.lostonyetes.androidengine;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import grupo01.lostonyetes.engine.IInput;
import grupo01.lostonyetes.engine.InputEvent;
import grupo01.lostonyetes.engine.InputType;

public class AInput implements IInput , View.OnTouchListener{

    List<InputEvent> eventList;
    List<InputEvent> eventsToHandled;

    public AInput(View view){
        this.eventList = new ArrayList<>();
        this.eventsToHandled = new ArrayList<>();
        if (view != null) {
            view.setOnTouchListener(this);
        }
    }

    @Override
    synchronized public List<InputEvent> getEvents() {
        eventList.clear();

        for (InputEvent i : eventsToHandled)
        {
            eventList.add(i);
        }

        eventsToHandled.clear();

        return eventList;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        InputType type = null;

        if (action == MotionEvent.ACTION_DOWN) {
            type = InputType.ONTOUCHDOWN;
        } else if (action == MotionEvent.ACTION_UP) {
            type = InputType.ONRELEASE;
        }

        if (type != null) {
            InputEvent ev = new InputEvent( x, y, type, 0);
            synchronized (this) {
                eventsToHandled.add(ev);
            }
        }

        return true; // Devuelve true para consumir el evento y seguir recibiendo ACTION_MOVE o ACTION_UP
    }
}
