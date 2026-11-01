package grupo01.lostonyetes.gamelogic;

import java.util.List;

import grupo01.lostonyetes.engine.IAudio;
import grupo01.lostonyetes.engine.IEngine;
import grupo01.lostonyetes.engine.IFont;
import grupo01.lostonyetes.engine.IGraphics;
import grupo01.lostonyetes.engine.IImage;
import grupo01.lostonyetes.engine.ISound;
import grupo01.lostonyetes.engine.IState;
import grupo01.lostonyetes.engine.InputEvent;
import grupo01.lostonyetes.engine.InputType;

public class StartScene implements IState {
    IGraphics mGraphics;
    IAudio mAudioSystem;
    IEngine mEngine;
    ISound mSonidoPrueba;
    IFont mFuentePrueba;

    Button mBotonPrueba;

    private Boolean var;

    public StartScene(IEngine eng){
        mEngine = eng;
        mGraphics = eng.getGraphics();
        mGraphics.SetLogicSize(700,500);
        mAudioSystem = eng.getAudio();
        mSonidoPrueba = mAudioSystem.newMusicTrack("efecto-damage.wav");
        //Cargamos una fuente de texto
        mFuentePrueba = mGraphics.newFont("Roboto-Regular.ttf", 30f, false, false);
        mBotonPrueba = new Button(200, 200, 100, 50,
                0xFF00FF00, 0xFF000000, mFuentePrueba, "Boton magico sii");
        this.var = false;
    }

    @Override
    public void init() {

    }

    @Override
    public void update(double dt) {

        //Prueba de efecto de sonido, en los primeros frames puede que no se reproduzca el sonido
        //this.mAudioSystem.playSoundEffect(mSonidoPrueba.getIdSound(), false);
        //Prueba de musica
//        if (!var){
//            this.mAudioSystem.playMusicTrack(mSonidoPrueba.getIdSound(), true);
//            var = true;
//        }
    }

    @Override
    public void render() {
        //Limpiar pantalla o pintar fondo con un rectángulo azul
        this.mGraphics.setColor(0xFF0000FF); // Alpha: FF, Rojo: 00, Verde: 00, Azul: FF
        this.mGraphics.fillRectangle(0, 0, 700,500);

        //Pintar un rectangulo verde
        this.mGraphics.setColor(0xFF00FF00); // Verde opaco
        //this.mGraphics.fillRectangle(x, y, w, h);
        this.mGraphics.fillCircle(100, 100,  100);

        //Probamos a pintar una imagen
        IImage miImagen = this.mGraphics.newImage("CuboMinecraft.png", 50,50, 50, 50);
        this.mGraphics.drawImage(miImagen);

        //Escribimos texto en pantalla
        this.mGraphics.setColor(0xFF000000);
        this.mGraphics.drawText("¡Hola Mundo!", 150, 100, this.mFuentePrueba, true);

        this.mBotonPrueba.render(this.mGraphics);
    }

    @Override
    public void handleInput(List<InputEvent> events) {
        for (InputEvent ev: events)
        {
            if(ev.getEventType() == InputType.ONTOUCHDOWN){
                //Prueba de efecto de sonido
                //this.mAudioSystem.playSoundEffect(mSonidoPrueba.getIdSound(), true);
                System.out.println("cacacacaca");
            }
        }
    }
}
