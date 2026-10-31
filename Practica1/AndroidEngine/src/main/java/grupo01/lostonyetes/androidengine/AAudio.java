package grupo01.lostonyetes.androidengine;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.io.IOException;
import java.util.HashMap;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.ISound;

public class AAudio implements IAudio {
    private final SoundPool soundPool; // para efectos de sonido, sonidos cortos, o sonidos poco pesados.
    private final MediaPlayer mediaPlayer; // para audios largos y musica
    private final AssetManager assetManager;

    // mapa de tracks que guarda el id del track de musica y su ruta de archivo
    private final HashMap<Integer, String> musicMap;

    // id del proximo track musical a guardar en el mapa
    private int nextMusicId;

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
                .setMaxStreams(10) // Es el numero maximo de sonidos reproduciendose a la vez, se podria parametrizar
                .setAudioAttributes(audioAttributes)
                .build();

        this.mediaPlayer = new MediaPlayer();
        this.mediaPlayer.reset();

        this.musicMap = new HashMap<>();
        this.nextMusicId = 10000; // numero grande para que no choque la reproduccion de efectos de sonido y de musica por accidente
    }

    /**
     * carga un nuevo efecto de sonido
     * @param file el archivo de efecto de sonido a cargar
     */
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

    /**
     * carga una nueva pista de audio/musica larga
     * @param file el archivo de audio, se guarda como referencia en una tabla para mas adelante
     */
    @Override
    public ISound newMusicTrack(String file) {
        int soundId = nextMusicId++;
        musicMap.put(soundId, file);
        return new ASound(soundId);
    }

    /**
     * reproduce una pista de audio/musica larga
     * @param id el identificador del audio a reproducirse
     * @param loop true = se repite la reproduccion del audio automaticamente
     */
    @Override
    public void playMusicTrack(int id, boolean loop) {
        if (musicMap.containsKey(id)) {
            String filename = musicMap.get(id);

            mediaPlayer.stop();
            mediaPlayer.reset();
            try {
                AssetFileDescriptor afd = assetManager.openFd("audio/music/"+filename);
                mediaPlayer.setDataSource(afd.getFileDescriptor(),
                        afd.getStartOffset(), afd.getLength());
                mediaPlayer.setLooping(loop);
                mediaPlayer.setVolume(1.0f, 1.0f);
                mediaPlayer.prepare();
                mediaPlayer.start();

            } catch (IOException e) {
                System.err.println("Couldn't load music file");
                e.printStackTrace();
            }
        }
    }

    /**
     * detiene la reproduccion de una pista de audio/musica larga
     * @param id el identificador del audio a detener
     */
    @Override
    public void stopMusicTrack(int id) {
        mediaPlayer.stop();
    }

    /**
     * reproduce un efecto de sonido una vez
     * @param id el identificador del efecto de sonido a reproducirse
     * @param loop true = se repite la reproduccion del efecto de sonido automaticamente
     */
    @Override
    public void playSoundEffect(int id, boolean loop) {
        int _loop = loop ? -1 : 0;
        if (id != -1 && !musicMap.containsKey(id)) {
            this.soundPool.play(id, 1.0f, 1.0f, 1, _loop, 1.0f);
        }
    }

    /**
     * detiene la reproduccion de un efecto de sonido,
     * a veces pueden durar por mas tiempo, o repetrirse continuamente, y queremos que se puedan parar
     * @param id el identificador del efecto de sonido a detener
     */
    @Override
    public void stopSoundEffect(int id) {
        if (id != -1 && !musicMap.containsKey(id)) {
            this.soundPool.stop(id);
        }
    }
}
