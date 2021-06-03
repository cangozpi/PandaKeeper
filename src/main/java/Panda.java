import java.util.ArrayList;


public class Panda {


    private int x;
    private int y;
    private int s;
    private static int Panda_num = 0;
    private ArrayList<Spot> spots;
    private int alreadyEatenStateCount = 0; //number of grids that panda is assigned to eat

    public Panda(int x, int y, int s){
        this.x = x;
        this.y = y;
        this.s = s;
        Panda_num += 1;
        System.out.println("Panda no: "+ Panda_num + " X: " + x + " Y: " + y + " S: " + s);

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

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getPanda_num(){return Panda_num;}
    public void setPanda_num(int panda_num) {
        Panda_num = panda_num;
    }

    public void resetPandaNo(){
        setPanda_num(0);
    }

    public void addAlreadyEatenCount(){
        this.alreadyEatenStateCount++;
    }

    public int getAlreadyEatenStateCount(){
        return alreadyEatenStateCount;
    }
}
