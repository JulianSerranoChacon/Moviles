package grupo01.lostonyetes.engine;

import java.util.List;

public interface IInput {
    public List<InputEvent> getEvents();
    void handleEvents ();
}
