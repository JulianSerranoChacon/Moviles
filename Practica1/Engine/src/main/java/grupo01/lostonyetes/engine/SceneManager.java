package grupo01.lostonyetes.engine;

public class SceneManager{
    IState ActualScene;
    IState NextScene;
    public SceneManager(){
        ActualScene = null;
        NextScene = null;
    }
    public void SetScene(IState scene){
        if(ActualScene == null) ActualScene = scene;
        else NextScene = scene;
    }

    public void init() {
        if(ActualScene != null) ActualScene.init();
    }

    public void update(double dt) {
        if(ActualScene != null) ActualScene.update(dt);
    }

    public void render() {
        if(ActualScene != null) ActualScene.render();
    }
    public void ChangeIfNextScene(){
        if(NextScene != null){
            ActualScene = NextScene;
            NextScene = null;
        }
    }
}
