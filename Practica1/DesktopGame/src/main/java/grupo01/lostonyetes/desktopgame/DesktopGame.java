package grupo01.lostonyetes.desktopgame;

import grupo01.lostonyetes.desktopengine.DesktopEngine;
import grupo01.lostonyetes.gamelogic.GameLogic;

public class DesktopGame {

    private static DesktopEngine engine;
    private static GameLogic logic;

    public static void main(String[] args) {

        engine = new DesktopEngine();

        engine.setWindowInfo("Los tonyetes",800 ,600);

        logic = new GameLogic(engine);
    }
}