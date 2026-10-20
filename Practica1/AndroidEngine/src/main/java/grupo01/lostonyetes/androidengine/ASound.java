package grupo01.lostonyetes.androidengine;

import grupo01.lostonyetes.engine.ISound;

public class ASound implements ISound {
    private int soundId;

    public ASound(int soundId){
        this.soundId = soundId;
    }

    public int getSoundId(){
        return soundId;
    }
}
