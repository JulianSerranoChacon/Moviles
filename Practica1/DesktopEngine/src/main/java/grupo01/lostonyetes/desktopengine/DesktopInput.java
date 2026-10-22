package grupo01.lostonyetes.desktopengine;

import java.util.List;

import grupo01.lostonyetes.engine.IInput;
import grupo01.lostonyetes.engine.InputEvent;

public class DesktopInput implements IInput {

    List<InputEvent> eventList;
    List<InputEvent> eventsToHandled;


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
    public void handleEvents() {

    }
}
