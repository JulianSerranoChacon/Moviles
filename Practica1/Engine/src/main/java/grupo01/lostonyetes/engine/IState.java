package grupo01.lostonyetes.engine;

public interface IState {
    void init();
    void update(double dt);
    void render();
}
