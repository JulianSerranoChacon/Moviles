package grupo01.lostonyetes.gamelogic;

import grupo01.lostonyetes.engine.IEngine;

public class GameLogic {
    IEngine mEngine;
    public GameLogic(IEngine engine){
        mEngine = engine;
        mEngine.getGraphics().setLogicSize(1920, 1080);
        GameLevelScene mScene = new GameLevelScene(engine);
        mEngine.setScene(mScene);
    }
}