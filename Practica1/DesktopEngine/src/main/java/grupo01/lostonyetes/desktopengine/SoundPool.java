package grupo01.lostonyetes.desktopengine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

public class SoundPool {

    private final int maxStreams;
    private final List<DesktopSound> activeSounds;

    public SoundPool(int maxStreams) {
        this.maxStreams = maxStreams;
        this.activeSounds = new ArrayList<>();
    }

    /**
     * Intenta reproducir un sonido siguiendo las reglas de prioridad y capacidad de Android.
     */
    public synchronized void playSound(DesktopSound sound, int id) {
        //Si la pool está llena, determinamos si podemos hacer hueco
        if (activeSounds.size() >= maxStreams) {
            DesktopSound candidateToEvict = findEvictionCandidate(sound.getPriority());

            if (candidateToEvict != null) {
                // Detenemos y liberamos el sonido de menor prioridad/más antiguo
                stopSound(candidateToEvict);
            } else {
                // Si no hay candidatos con menor o igual prioridad, el sonido nuevo se descarta
                return;
            }
        }

        /*
        // Reproducimos el nuevo sonido en un hilo secundario usando la API javax.sound de Java Desktop
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();

            clip.open(audioStream);

            // Marcamos el timestamp actual y el estado activo
            sound.setTimeStamp(System.currentTimeMillis());
            sound.setPlaying(true);
            activeSounds.add(sound);

            // Listener para limpiar el sonido cuando termine de sonar
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                    sound.setPlaying(false);
                    synchronized (SoundPool.this) {
                        activeSounds.remove(sound);
                    }
                }
            });

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
            sound.setPlaying(false);
            activeSounds.remove(sound);
        }*/
    }

    /**
     * Busca qué sonido debe ser interrumpido si la pool está llena:
     * - Busca el sonido activo con la prioridad más baja.
     * - Si hay varios con la misma prioridad más baja, elige el más antiguo (menor timestamp).
     * - Si el nuevo sonido no supera en prioridad al peor activo, devuelve null (se descarta el nuevo).
     */
    private DesktopSound findEvictionCandidate(int newPriority) {
        DesktopSound lowestPrioritySound = null;

        for (DesktopSound active : activeSounds) {
            if (lowestPrioritySound == null) {
                lowestPrioritySound = active;
                continue;
            }

            // Selecciona si la prioridad es menor
            if (active.getPriority() < lowestPrioritySound.getPriority()) {
                lowestPrioritySound = active;
            }
            // Criterio de desempate (Misma prioridad): el más antiguo (menor timeStamp)
            else if (active.getPriority() == lowestPrioritySound.getPriority()) {
                if (active.getTimeStamp() < lowestPrioritySound.getTimeStamp()) {
                    lowestPrioritySound = active;
                }
            }
        }

        // Si la prioridad del candidato es menor, se desaloja.
        // Si es igual, desaloja al candidato antiguo solo si el nuevo tiene AL MENOS esa prioridad.
        if (lowestPrioritySound != null && newPriority >= lowestPrioritySound.getPriority()) {
            return lowestPrioritySound;
        }

        return null;
    }

    public synchronized void stopSound(DesktopSound sound) {
        sound.setPlaying(false);
        activeSounds.remove(sound);
    }
}