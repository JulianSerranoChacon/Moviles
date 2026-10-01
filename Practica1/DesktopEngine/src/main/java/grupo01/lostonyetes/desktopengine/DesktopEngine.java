package grupo01.lostonyetes.desktopengine;

import java.security.PublicKey;
import java.util.ArrayList;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.IEngine;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IState;

public class DesktopEngine implements IEngine, IState {
    DesktopGraphics mDesktopGraphics;
    DesktopAudio mDesktopAudio;
    public DesktopEngine(){
        mDesktopGraphics = new DesktopGraphics();
        mDesktopAudio = new DesktopAudio();
    }
    @Override
    public IGraphics getGraphics(){
        return mDesktopGraphics;
    }
    @Override
    public IAudio getAudio(){
        return mDesktopAudio;
    }
    @Override
    public ArrayList getInput(){
        return null;
    }
    @Override
    public void setState(){

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