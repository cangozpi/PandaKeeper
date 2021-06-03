import java.util.*;
import java.util.stream.Collectors;

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

    public int solutionA(){
        //A > B
        //initialize grid state space
        ArrayList<Panda>[][]  spots = new  ArrayList[gardenWidth][gardenHeight]; //x,y

        //iterate through panda's and update grid values by adding panda names where they can eat
        for(Panda panda : pandaList){
            int currentX = panda.getX();
            int currentY = panda.getY();
            int pandaS = panda.getS();

            //top left corner of the grid
            int topLeftCornerX = currentX - ((pandaS-1)/2);
            int topLeftCornerY = currentY - ((pandaS-1)/2);




            for(int i = topLeftCornerX; i < topLeftCornerX +pandaS; i++) {
                for (int j = topLeftCornerY; j < topLeftCornerY + pandaS; j++) {

                    //check if the current state is within the boundaries of the grid space
                    if ((i < 0) | (j < 0) | (i > gardenWidth) | (j > gardenHeight)) {
                        continue;
                    }

                    spots[i][j].add(panda);


                }
            }
        }

        //making states with panda initialized in them uneatable
        for(Panda panda : pandaList) {
            int currentX = panda.getX();
            int currentY = panda.getY();
            int pandaS = panda.getS();
            spots[currentX][currentY] = new ArrayList<Panda>();
        }


        //filter the states that can be reached and eaten by pandas and map that information to sortedNonEmptyList
        ArrayList<ArrayList<Panda>> sortedNonEmptyList = (ArrayList<ArrayList<Panda>>) Arrays.stream(spots).filter(state -> state.length > 0).sorted(new Comparator<ArrayList<Panda>[]>() {
            @Override
            public int compare(ArrayList<Panda>[] arrayLists, ArrayList<Panda>[] t1) {
                return t1.length - arrayLists.length; //sorts in descending order
            }
        }).collect(Collectors.toList());

        //assign states with only 1 panda in i to that panda to eat
        for ( ArrayList<Panda> spot: sortedNonEmptyList) {
            if(spot.size() == 1){
                pandaList.get(spot.get(0).getPanda_num()).addAlreadyEatenCount();
            }else{
                for(panda : spot){
                    pandaList.get(panda.ge)
                }
            }
        }

        //assign one of the states that is shared between pandas to the panda that has eaten the least up to that point(might use PQ)

        return 0;
    }

    public class Coordinate{
        private int x;
        private int y;
        public Coordinate(int x, int y){
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
    }

    public int solutionB(){
        HashMap<Coordinate,PandaArea> pandaAreaMap = new HashMap<Coordinate,PandaArea>();




        for(int i = 0; i < pandaList.size(); i++){
            Panda curPanda = pandaList.get(i);
            int s = curPanda.getS();
            int x = curPanda.getX();
            int y = curPanda.getY();
            PandaArea p = new PandaArea(x,y,s);



        }

        for(int i = 0; i < pandaList.size(); i++){
            Panda curPanda = pandaList.get(i);
            int s = curPanda.getS();
            int x = curPanda.getX();
            int y = curPanda.getY();

            if(x-s> 0){
                if (y-s>0){
                    //top left corner in garden
                    for(int j = x-s; j< x+s; j++){     //x coord loop
                        if(j > gardenWidth){
                            break;
                        }
                        for(int k = y-s; k< y+s; k++){     //y coord loop
                            if(k > gardenHeight){
                                break;





                            }
                        }
                    }

                }else{
                    //top left corner y coord not in garden
                    for(int j = x-s; j< x+s; j++){     //x coord loop
                        if(j > gardenWidth){
                            break;
                        }
                        for(int k = 0; k< y+s; k++){     //y coord loop
                            if(k > gardenHeight){
                                break;
                            }
                        }
                    }
                }
            }else if(y-s>0){
                //top left corner x coord not in garden
                for(int j = 0; j< x+s; j++){     //x coord loop
                    if(j > gardenWidth){
                        break;
                    }
                    for(int k = y-s; k< y+s; k++){     //y coord loop
                        if(k > gardenHeight){
                            break;
                        }
                    }
                }
            }else{
                //top left corner x and y coord not in garden
                for(int j = 0; j< x+s; j++){     //x coord loop
                    if(j > gardenWidth){
                        break;
                    }
                    for(int k = 0; k< y+s; k++){     //y coord loop
                        if(k > gardenHeight){
                            break;
                        }
                    }
                }
            }




        }


        return 0;
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