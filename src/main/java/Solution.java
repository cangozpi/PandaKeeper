import java.util.ArrayList;
import java.util.List;

public class Solution {

    private int gardenHeight;
    private int gardenWidth;
    private int pandaNum;
    private ArrayList<Panda> pandaList = new ArrayList<Panda>();



    public Solution(int height, int width, int numPandas) {
        this.gardenHeight = height;
        this.gardenWidth = width;
        this.pandaNum = pandaNum;
    }


    public void addPanda(int pandaX, int pandaY, int pandaS){
        pandaList.add(new Panda(pandaX, pandaY,pandaS));
    }

    public int getGardenHeight() {
        return gardenHeight;
    }

    public void setGardenHeight(int gardenHeight) {
        this.gardenHeight = gardenHeight;
    }

    public int getGardenWidth() {
        return gardenWidth;
    }

    public void setGardenWidth(int gardenWidth) {
        this.gardenWidth = gardenWidth;
    }

    public int getPandaNum() {
        return pandaNum;
    }

    public void setPandaNum(int pandaNum) {
        this.pandaNum = pandaNum;
    }

    public ArrayList<Panda> getPandaList() {
        return pandaList;
    }

    public void setPandaList(ArrayList<Panda> pandaList) {
        this.pandaList = pandaList;
    }


}