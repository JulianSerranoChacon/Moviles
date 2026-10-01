package grupo01.lostonyetes.desktopengine;

import java.security.PublicKey;
import java.util.ArrayList;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.IEngine;
import grupo01.lostonyetes.engine.IGraphics;

public class DesktopEngine implements IEngine {
    DesktopGraphics mDesktopGraphics;
    DesktopAudio mDesktopAudio;
    public DesktopEngine(){
        mDesktopGraphics = new DesktopGraphics();
        mDesktopAudio = new DesktopAudio();
    }
    public IGraphics getGraphics(){
        return mDesktopGraphics;
    }
    public IAudio getAudio(){
        return mDesktopAudio;
    }
    public ArrayList getInput(){
        return null;
    }
    public void setState(){

    }
}