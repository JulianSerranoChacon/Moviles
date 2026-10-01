package grupo01.lostonyetes.desktopengine;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.ISound;

public class DesktopAudio implements IAudio {
    public  DesktopAudio(){

    }
    public ISound newSound() {
        return new DesktopSound();
    }
    public void playSound(){

    }
    public void stopSound(){

    }
}
