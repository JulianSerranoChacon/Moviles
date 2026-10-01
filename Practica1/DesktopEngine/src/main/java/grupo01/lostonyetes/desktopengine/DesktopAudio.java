package grupo01.lostonyetes.desktopengine;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.ISound;

public class DesktopAudio implements IAudio {
    public  DesktopAudio(){

    }
    @Override
    public ISound newSound() {
        return new DesktopSound();
    }
    @Override
    public void playSound(){

    }
    @Override
    public void stopSound(){

    }
}
