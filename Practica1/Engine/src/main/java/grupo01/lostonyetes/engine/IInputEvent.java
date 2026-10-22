package grupo01.lostonyetes.engine;

import java.util.List;

public interface IInputEvent {


    InputEvent getEventType();

    int getEventID();

    int getPosX();

    int getPosY();

    List<InputEvent> getEvents();
}
