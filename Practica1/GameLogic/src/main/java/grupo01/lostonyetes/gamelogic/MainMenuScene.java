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

public class MainMenuScene implements IState {
    IGraphics mGraphics;
    IEngine mEngine;
    IFont mFuente;

    ChangeSceneButton mBotonJugar;
    ChangeSceneButton mBotonRetos;

    public MainMenuScene(IEngine eng){
        mEngine = eng;
        mGraphics = eng.getGraphics();
        GameLevelScene mGameScene = new GameLevelScene(eng);

        mFuente = mGraphics.newFont("Roboto-Regular.ttf", 30f, false, false);
        mBotonJugar = new ChangeSceneButton(200, 200, 100, 50,
                0xFFFF0000, 0xFF000000, mFuente, "JUGAR", eng, mGameScene);
        mBotonRetos = new ChangeSceneButton(200, 300, 100, 50,
                0xFFFF0000, 0xFF000000, mFuente, "RETOS", eng, mGameScene);
    }

    @Override
    public void init() {

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void render() {
        //Escribimos texto en pantalla
        this.mGraphics.setColor(0xFF000000);
        this.mGraphics.drawText("Super Jet Pack", 300, 100, this.mFuente, true);

        this.mBotonJugar.render(this.mGraphics);
        this.mBotonRetos.render(this.mGraphics);
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
