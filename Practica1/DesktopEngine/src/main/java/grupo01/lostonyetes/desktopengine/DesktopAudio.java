package grupo01.lostonyetes.desktopengine;

import javax.sound.sampled.Clip;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.ISound;

public class DesktopAudio implements IAudio {
    String root = "/assets/";
    public  DesktopAudio() {

    }

    @Override
    public ISound newSound(String file) {
        //return new DesktopSound(file);
        return null;
    }

    @Override
    public void playSound(ISound sound, boolean loop) {
        Clip clip = ((DesktopSound)sound).getSoudClip();
        clip.setFramePosition(0);
        if (loop){
            clip.setLoopPoints(0, 1);
        }
    }

    @Override
    public void stopSound(ISound sound) {

    }

    @Override
    public void mute() {

    }

    @Override
    public void unmute() {

    }
}
