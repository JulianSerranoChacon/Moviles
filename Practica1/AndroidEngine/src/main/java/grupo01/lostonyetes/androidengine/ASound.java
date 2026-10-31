package grupo01.lostonyetes.androidengine;

import grupo01.lostonyetes.engine.ISound;

public class ASound implements ISound {
    private final int idSound;

    public ASound(int idSound){
        this.idSound = idSound;
    }
    @Override
    public int getIdSound() {
        return idSound;
    }
}
