package grupo01.lostonyetes.engine;

import java.util.List;

public interface IState {
    void init();
    void update(double dt);
    void render();

    void handleInput(List<InputEvent> events);
}
