package grupo01.lostonyetes.desktopengine;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.ISound;

public class DesktopAudio implements IAudio {
    public  DesktopAudio(){

    }

    @Override
    public ISound newSoundEffect(String file) {
        return null;
    }

    @Override
    public ISound newMusicTrack(String file) {
        return null;
    }

    @Override
    public void playMusicTrack(int id, boolean loop) {

    }

    @Override
    public void stopMusicTrack(int id) {

    }

    @Override
    public void playSoundEffect(int id, boolean loop) {

    }

    @Override
    public void stopSoundEffect(int id) {

    }
}
