package grupo01.lostonyetes.androidengine;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.io.IOException;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.ISound;

public class AAudio implements IAudio {
    private final SoundPool soundPool;
    private final MediaPlayer mediaPlayer;
    private final AssetManager assetManager;

    /**
     * motor debe recibir el contexto de la actividad
     * @param context la Activity que inicializa el juego
     */
    public AAudio(Context context) {
        this.assetManager = context.getAssets();

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        this.soundPool = new SoundPool.Builder()
                .setMaxStreams(1000) // Es el numero maximo de sonidos reproduciendose a la vez
                .setAudioAttributes(audioAttributes)
                .build();

        this.mediaPlayer = new MediaPlayer();
        this.mediaPlayer.reset();
    }
    @Override
    public ISound newSoundEffect(String file) {
        int soundId = -1;
        try {
            AssetFileDescriptor assetDescriptor =
                    assetManager.openFd("audio/sfx/"+file);
            soundId = soundPool.load(assetDescriptor, 1);
        }catch (IOException e) {
            throw new RuntimeException("Couldn't load soundEffect.");
        }

        return new ASound(soundId);
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
        if (id != -1) {
            this.soundPool.play(id, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    @Override
    public void stopSoundEffect(int id) {
        if (id != -1) {
            this.soundPool.stop(id);
        }
    }
}
