import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;


public class Panda {

    private int x;
    private int y;
    private int s;
    private static int Panda_num = 0;
    private HashMap<Spot.Coordinate,Spot> spots = new HashMap<Spot.Coordinate, Spot>();
    private int alreadyEatenStateCount = 0; //number of grids that panda is assigned to eat
    private int pandaNum;

    private HashSet<Panda> neighbours = new HashSet<>();
    public Panda(int x, int y, int s){
        this.x = x;
        this.y = y;
        this.s = s;
        Panda_num += 1;
        pandaNum = Panda_num;
        System.out.println("Panda no: "+ Panda_num + " X: " + x + " Y: " + y + " S: " + s);

    }
    public Panda(int x, int y, int s, int alreadyEatenStateCount, int pandaNum){
        this.x = x;
        this.y = y;
        this.s = s;
        this.pandaNum = pandaNum;
        this.alreadyEatenStateCount = alreadyEatenStateCount;
    }


    public void addNeighbours(HashSet<Panda> a){
        if(a != null)neighbours.addAll(a);
    }

    public HashSet<Panda> getNeighbours(){return neighbours;}
    public void giveBamboo(){
        if(neighbours!=null) {
            int max = 0;
            Panda m = null;
            for (Panda panda : neighbours) {
                if (this == panda) continue;
                if (panda.getAlreadyEatenStateCount() > max) {
                    m = panda;
                }
            }
            if (m == null) return;
            if (m.alreadyEatenStateCount > alreadyEatenStateCount) {
                alreadyEatenStateCount++;
                m.giveBamboo();
                m.decreaseBamboo();
            }
        }
    }
    public void decreaseBamboo(){
        alreadyEatenStateCount--;
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

    public static void resetPandaNo(){
        Panda_num = 0;
    }

    public void addAlreadyEatenCount(){
        alreadyEatenStateCount++;
    }

    public void decrementAlreadyEatenCount(){
        alreadyEatenStateCount--;
    }

    public void addSpot(Spot s){
       spots.put(s.getCoordinate(),s);
    }

    public void removeSpot(Spot s){
        spots.remove(s.getCoordinate());
    }


    public int getAlreadyEatenStateCount(){
        return alreadyEatenStateCount;
    }

    public int getPandaNum() {
        return pandaNum;
    }

    public void setPandaNum(int pandaNum) {
        this.pandaNum = pandaNum;
    }

    public void setAlreadyEatenStateCount(int num){
        this.alreadyEatenStateCount = num;
    }
}
