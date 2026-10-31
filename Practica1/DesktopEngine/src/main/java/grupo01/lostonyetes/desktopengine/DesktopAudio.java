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
    public void playMusicTrack() {

    }

    @Override
    public void stopMusicTrack() {

    }

    @Override
    public void playSoundEffect(int id) {

    }

    @Override
    public void stopSoundEffect(int id) {

    }
}
