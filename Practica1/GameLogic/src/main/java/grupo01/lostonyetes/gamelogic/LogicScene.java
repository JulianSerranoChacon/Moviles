package grupo01.lostonyetes.gamelogic;

import java.util.ArrayList;
import java.util.List;

import grupo01.lostonyetes.engine.IEngine;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IState;
import grupo01.lostonyetes.engine.InputEvent;

public class LogicScene implements IState {

    IGraphics mGraphics;

    List<GameObject> gameObjectList;

    LogicScene(IEngine eng){
        this.mGraphics = eng.getGraphics();
        gameObjectList = new ArrayList<>();
    }

    @Override
    public void init() {

    }

    @Override
    public void update(double dt) {
        for(GameObject g : gameObjectList)
            g.update(dt);
    }

    @Override
    public void render() {
        //Escribimos texto en pantalla

        for(GameObject g : gameObjectList)
            g.render(mGraphics);
    }

    @Override
    public void handleInput(List<InputEvent> events) {
        for(GameObject g : gameObjectList)
            g.handleInput(events);
    }
}
