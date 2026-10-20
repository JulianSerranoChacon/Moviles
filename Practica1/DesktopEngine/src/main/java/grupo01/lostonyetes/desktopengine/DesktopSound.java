package grupo01.lostonyetes.desktopengine;

import javax.sound.sampled.Clip;

import grupo01.lostonyetes.engine.ISound;

public class DesktopSound implements ISound {
    private final Clip clip;
    public DesktopSound(Clip clip){

        this.clip = clip;
    }
    public Clip getSoudClip(){return clip;}
}
