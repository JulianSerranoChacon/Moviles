package grupo01.lostonyetes.desktopgame;

import grupo01.lostonyetes.desktopengine.DesktopEngine;

public class DesktopGame {

    private static DesktopEngine engine;

    public static void main(String[] args) {

        engine = new DesktopEngine();

        engine.setWindowInfo("Los tonyetes", 800,600);
    }
}