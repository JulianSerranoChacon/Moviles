package grupo01.lostonyetes.gamelogic;

import java.util.List;

import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.InputEvent;

public interface GameObject {
    void init();
    void update(double dt);
    void render(IGraphics graphics);

    void handleInput(List<InputEvent> events);
}
