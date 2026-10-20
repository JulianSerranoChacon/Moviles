package grupo01.lostonyetes.engine;

public abstract class ScalableGraphics implements IGraphics{
    protected  int logicWidth = 700;
    protected  int logicHeight = 500;
    protected float offsetX, offsetY, scale;

    public void SetLogicSize(int lw, int lh){
        logicWidth = lw;
        logicHeight = lh;
    }
    public int GetWidth() {
        return logicWidth;
    }
    public abstract int GetRealWidth();
    public int GetHeight() {
        return logicHeight;
    }
    public abstract int GetRealHeight();

    protected abstract void scale(float x, float y);
    protected abstract void translate(float x, float y);
    protected abstract void AspectRatio();
    public int ConvertCoordsX(float x){
        return (int)((x -offsetX)/scale);
    }
    public int ConvertCoordsY(float y){
        return (int)((y -offsetY)/scale);
    }
}
