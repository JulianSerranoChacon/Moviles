package grupo01.lostonyetes.androidengine;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;

import java.io.IOException;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.ISound;

public class AAudio implements IAudio {

    private final AssetManager assetManager;
    private SoundPool soundpool;

    protected AAudio (Context context) {
        this.assetManager = context.getAssets();
        this.soundpool = new SoundPool.Builder().setMaxStreams(10).build(); // tenemos que parametrizar el numero de pistas simultaneas.
    }

    @Override
    public ISound newSound(String file) {
        ASound sound = null;
        int soundId = -1;
        try {
            AssetFileDescriptor assetDescriptor =
                    assetManager.openFd(file);
            soundId = this.soundpool.load(assetDescriptor, 1);
        }catch (IOException e) {
            throw new RuntimeException("Couldn't load sound.");
        }

        return new ASound(soundId);
    }

    @Override
    public void playSound(ISound sound, boolean loop) {
        this.soundpool.play(((ASound)sound).getSoundId(), 1, 1, 1, 0, 1);
    }

    @Override
    public void stopSound(ISound sound) {
        this.soundpool.stop(((ASound)sound).getSoundId());
    }

    @Override
    public void mute() {

    }

    @Override
    public void unmute() {

    }
}
