package grupo01.lostonyetes.desktopengine;

import grupo01.lostonyetes.engine.ISound;

public class DesktopSound implements ISound {

    private float timeStamp;
    private volatile boolean isPlaying;
    private final int id_Sound;
    private int priority;

    public DesktopSound(int idSound) {
        this(idSound, 1);
    }

    public DesktopSound(int idSound, int priority) {
        this.id_Sound = idSound;
        this.timeStamp = 0;
        this.isPlaying = false;
        this.priority = priority;
    }

    @Override
    public int getIdSound() {
        return this.id_Sound; // Corregido: antes devolvía 0
    }

    public void setTimeStamp(float tS) { this.timeStamp = tS; }
    public float getTimeStamp() { return this.timeStamp; }

    public void setPlaying(boolean playing) { this.isPlaying = playing; }
    public boolean isPlaying() { return this.isPlaying; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
}