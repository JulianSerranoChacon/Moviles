package grupo01.lostonyetes.engine;

public interface IAudio {
    ISound newSoundEffect(String file);
    ISound newMusicTrack(String file);
    void playMusicTrack();
    void stopMusicTrack();
    void playSoundEffect(int id);
    void stopSoundEffect(int id);
}
