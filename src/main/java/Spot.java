import java.util.ArrayList;

public class Spot {

    private int x;
    private int y;
    private boolean hasPanda;
    private int[] pandas;


    public Spot(int x, int y){
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isHasPanda() {
        return hasPanda;
    }

    public void setHasPanda(boolean hasPanda) {
        this.hasPanda = hasPanda;
    }
}
