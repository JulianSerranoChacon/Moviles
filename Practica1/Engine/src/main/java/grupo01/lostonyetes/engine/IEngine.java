package grupo01.lostonyetes.engine;
import java.io.InputStream;
import java.util.ArrayList;

public interface IEngine {
    InputStream openInputStream(String filepath);
    IGraphics getGraphics();
    IAudio getAudio();
    ArrayList getInput();
    void setState();


}
