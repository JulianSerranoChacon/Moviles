package grupo01.lostonyetes.engine;
import java.util.ArrayList;

public interface IEngine {
    IGraphics getGraphics();
    IAudio getAudio();
    ArrayList getInput();
    void setState();


}
