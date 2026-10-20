package grupo01.lostonyetes.engine;

public interface IAudio {
    public ISound newSound(String file);
    public void playSound(ISound sound, boolean loop);
    public void stopSound(ISound sound);
    public void mute();
    public void unmute();
}
