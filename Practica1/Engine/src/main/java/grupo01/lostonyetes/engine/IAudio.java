package grupo01.lostonyetes.engine;

public interface IAudio {
    ISound newSoundEffect(String file);
    ISound newMusicTrack(String file);
    void playMusicTrack(int id, boolean loop);
    void stopMusicTrack(int id);
    void playSoundEffect(int id, boolean loop);
    void stopSoundEffect(int id);
}
