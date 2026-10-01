import java.util.ArrayList;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.IEngine;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IState;

public class AndroidEngine implements IEngine, IState {
    @Override
    public IGraphics getGraphics() {
        return null;
    }

    @Override
    public IAudio getAudio() {
        return null;
    }

    @Override
    public ArrayList getInput() {
        return null;
    }

    @Override
    public void setState() {

    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }
}
