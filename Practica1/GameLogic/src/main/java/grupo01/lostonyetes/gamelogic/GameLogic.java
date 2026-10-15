package grupo01.lostonyetes.gamelogic;

import grupo01.lostonyetes.engine.IEngine;

public class GameLogic {
    IEngine mEngine;
    public GameLogic(IEngine engine){
        mEngine = engine;
        StartScene mScene = new StartScene(engine);
        mEngine.setScene(mScene);
    }
}