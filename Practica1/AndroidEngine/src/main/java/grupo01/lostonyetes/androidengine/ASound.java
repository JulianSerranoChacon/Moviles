package grupo01.lostonyetes.androidengine;

import grupo01.lostonyetes.engine.ISound;

public class ASound implements ISound {
    private final int id_Sound;

    public ASound(int idSound){
        this.id_Sound = idSound;
    }
    @Override
    public int getIdSound() {
        return id_Sound;
    }
}
