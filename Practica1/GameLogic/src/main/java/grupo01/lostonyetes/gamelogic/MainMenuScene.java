package grupo01.lostonyetes.gamelogic;

import java.util.ArrayList;
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

public class MainMenuScene extends LogicScene {
    IEngine mEngine;

    ChangeSceneButton mBotonRetos;

    public MainMenuScene(IEngine eng){
        super(eng);
        mEngine = eng;
        GameLevelScene mGameScene = new GameLevelScene(eng);


        IFont mFuente = mGraphics.newFont("Roboto-Regular.ttf", 30f, false, false);

        TextObject text = new TextObject(mFuente, "Super Jet Pack",300,100);
        gameObjectList.add(text);

        ChangeSceneButton mBotonJugar = new ChangeSceneButton(200, 200, 100, 50,
                0xFFFF0000, 0xFF000000, mFuente, "JUGAR", eng, mGameScene);
        gameObjectList.add(mBotonJugar);

        mBotonRetos = new ChangeSceneButton(200, 300, 100, 50,
                0xFFFF0000, 0xFF000000, mFuente, "RETOS", eng, mGameScene);
        gameObjectList.add(mBotonRetos);
    }
}
