package grupo01.lostonyetes.practica1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import grupo01.lostonyetes.androidengine.AndroidEngine;
import grupo01.lostonyetes.gamelogic.GameLogic;

public class AndroidGame extends AppCompatActivity {

    private AndroidEngine engine;
    private GameLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Instanciamos el motor
        engine = new AndroidEngine(this);

        //Le pedimos la SurfaceView al motor y la metemos en pantalla
        setContentView(engine.getLayoutView());
        logic = new GameLogic(engine);
    }
}