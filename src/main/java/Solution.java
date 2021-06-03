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

    public int solutionA() {
        //A > B
        //initialize grid state space
        ArrayList<Panda>[][] spots = new ArrayList[gardenWidth][gardenHeight]; //x,y

        //iterate through panda's and update grid values by adding panda names where they can eat
        for (Panda panda : pandaList) {
            int currentX = panda.getX();
            int currentY = panda.getY();
            int pandaS = panda.getS();

            //top left corner of the grid
            int topLeftCornerX = currentX - pandaS;
            int topLeftCornerY = currentY - pandaS;


            for (int i = topLeftCornerX; i <= currentX + pandaS; i++) {
                for (int j = topLeftCornerY; j <= currentY + pandaS; j++) {

                    //check if the current state is within the boundaries of the grid space
                    if ((i < 0) | (j < 0) | (i >= gardenWidth) | (j >= gardenHeight)) {
                        continue;
                    }

                    if (spots[i][j] == null) {
                        spots[i][j] = new ArrayList<>();
                    }


                    spots[i][j].add(panda);


                }
            }
        }

        //making states with panda initialized in them are not edible
        for (Panda panda : pandaList) {
            int currentX = panda.getX();
            int currentY = panda.getY();

            spots[currentX][currentY] = new ArrayList<Panda>();
        }


        //filter the states that can be reached and eaten by pandas and map that information to sortedNonEmptyList
        //ArrayList<ArrayList<Panda>

        ArrayList<ArrayList<Panda>> sortedNonEmptyList = new ArrayList<>();

        for (int i = 0; i < spots.length; i++) {
            for (int j = 0; j < spots[0].length; j++) {
                //System.out.println(spots[i][j]);
                ArrayList<Panda> state = spots[i][j];
                if(state != null){
                    if (state.size() > 0) {
                        sortedNonEmptyList.add(state);
                    }
                }

            }
        }
            sortedNonEmptyList.sort(new Comparator<ArrayList<Panda>>() {
                @Override
                public int compare(ArrayList<Panda> pandas, ArrayList<Panda> t1) {
                    return  pandas.size() - t1.size(); //sorts in descending order
                }
            });


         /*sortedNonEmptyList = Arrays.stream(spots).filter(state -> state.length > 0).sorted(new Comparator<ArrayList<Panda>[]>() {
            @Override
            public int compare(ArrayList<Panda>[] arrayLists, ArrayList<Panda>[] t1) {
                return t1.length - arrayLists.length; //sorts in descending order
            }
        }).collect();*/


            //assign states with only 1 panda in i to that panda to ea
            for (ArrayList<Panda> spot : sortedNonEmptyList) {
                if (spot.size() == 1) {//if only 1 panda can reach the state than assign that state to the corresponding panda
                    pandaList.get(spot.get(0).getPandaNum() - 1).addAlreadyEatenCount();
                } else {//if more than 1 panda can reach the given state then assign that spot to the panda who has eaten the least up to that point
                    int leastFedCount = 9999;// 9999 represents pos_infinity
                    int leastFedPanda = 0;



                    for (Panda panda : spot) {
                        int fedCount = panda.getAlreadyEatenStateCount();
                        if (fedCount < leastFedCount) {
                            leastFedCount = fedCount;
                            leastFedPanda = panda.getPandaNum() - 1;
                        }
                    }
                    pandaList.get(leastFedPanda).addAlreadyEatenCount();//least eaten panda gets to eat the current state
                }
            }

            //find the max eaten and min eaten spot by a given panda
            int maxFed = 0;
            int leastFed = 9999;
            for (Panda panda : pandaList) {
                int fedCount = panda.getAlreadyEatenStateCount();
                if (fedCount > maxFed) maxFed = fedCount;
                if (fedCount < leastFed) leastFed = fedCount;
            }


            return maxFed - leastFed; //return the max difference
        }

/*

    public int solutionAImproved(){
        //A > B
        //initialize grid state space

        ArrayList<Panda>[][]  spots = new  ArrayList[gardenWidth][gardenHeight]; //x,y
        HashMap<Coordinate,ArrayList<Panda>> map = new HashMap<>();
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
                   Coordinate coordinate = new Coordinate(i,j);
                   if(map.get(coordinate) != null){
                       map.get(coordinate).add(panda);
                   }else{
                       map.put(coordinate,new ArrayList<Panda>(Arrays.asList(panda)));
                   }



                }
            }
        }

        //making states with panda initialized in them uneatable
        for(Panda panda : pandaList) {
            int currentX = panda.getX();
            int currentY = panda.getY();
            map.remove(new Coordinate(currentX,currentY));
        }


        //filter the states that can be reached and eaten by pandas and map that information to sortedNonEmptyList
        //ArrayList<ArrayList<Panda>

        List<List<Panda>[]> sortedNonEmptyList = Arrays.stream(map).filter(state -> state.size > 0).sorted(new Comparator<ArrayList<Panda>[]>() {
            @Override
            public int compare(ArrayList<Panda>[] arrayLists, ArrayList<Panda>[] t1) {
                return t1.length - arrayLists.length; //sorts in descending order
            }
        }).collect( Collectors.toList());

        ArrayList<ArrayList<Panda>> sortedNonEmptyList2 = new ArrayList(sortedNonEmptyList);


        //assign states with only 1 panda in i to that panda to eat
        for ( ArrayList<Panda> spot: sortedNonEmptyList2) {
            if(spot.size() == 1){//if only 1 panda can reach the state than assign that state to the corresponding panda
                pandaList.get(spot.get(0).getPanda_num()).addAlreadyEatenCount();
            }else{//if more than 1 panda can reach the given state then assign that spot to the panda who has eaten the least up to that point
                int leastFedCount = 9999;// 9999 represents pos_infinity
                int leastFedPanda = 0;
                for(Panda panda : spot){
                    int fedCount = pandaList.get(panda.getPanda_num()).getAlreadyEatenStateCount();
                    if(fedCount < leastFedCount){
                        leastFedCount = fedCount;
                        leastFedCount = panda.getPanda_num();
                    }
                }
                pandaList.get(leastFedPanda).addAlreadyEatenCount();//least eaten panda gets to eat the current state
            }
        }

        //find the max eaten and min eaten spot by a given panda
        int maxFed = 0;
        int leastFed = 9999;
        for(Panda panda : pandaList){
            int fedCount = panda.getAlreadyEatenStateCount();
            if(fedCount>maxFed)maxFed = fedCount;
            if(fedCount<leastFed)leastFed = fedCount;
        }


        return maxFed -leastFed; //return the max difference
    }
*/

        public class Coordinate {
            private int x;
            private int y;

            public Coordinate(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public int hashCode() {
                int hash = 17;
                hash = 31 * hash + this.x;
                hash = 31 * hash + this.y;
                return hash;
            }

            @Override
            public boolean equals(Object other) {
                if (this == other) return true;
                if (other == null) return false;
                if (this.getClass() != other.getClass()) return false;
                Coordinate that = (Coordinate) other;
                return (this.x == that.x) && (this.y == that.y);
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


        public int solutionB() {


        HashMap<Coordinate,PandaArea> pandaAreaMap = new HashMap<Coordinate,PandaArea>();
        for(int i = 0; i < pandaList.size(); i++){
            Panda curPanda = pandaList.get(i);
            int s = curPanda.getS();
            int x = curPanda.getX();
            int y = curPanda.getY();
            PandaArea p = new PandaArea(x,y,s);

        }




            for (int i = 0; i < pandaList.size(); i++) {
                Panda curPanda = pandaList.get(i);
                int s = curPanda.getS();
                int x = curPanda.getX();
                int y = curPanda.getY();
                if (x - s > 0) {
                    if (y - s > 0) {
                        //top left corner in garden
                        for (int j = x - s; j < x + s; j++) {     //x coord loop
                            if (j > gardenWidth) {
                                break;
                            }
                            for (int k = y - s; k < y + s; k++) {     //y coord loop
                                if (k > gardenHeight) {
                                    break;

                                }
                                Spot newS = new Spot(j, k);
                                curPanda.addSpot(newS);
                            }
                        }

                    } else {
                        //top left corner y coord not in garden
                        for (int j = x - s; j < x + s; j++) {     //x coord loop
                            if (j > gardenWidth) {
                                break;
                            }
                            for (int k = 0; k < y + s; k++) {     //y coord loop
                                if (k > gardenHeight) {
                                    break;

                                }
                                Spot newS = new Spot(j, k);
                                curPanda.addSpot(newS);
                            }
                        }
                    }
                } else if (y - s > 0) {
                    //top left corner x coord not in garden
                    for (int j = 0; j < x + s; j++) {     //x coord loop
                        if (j > gardenWidth) {
                            break;
                        }
                        for (int k = y - s; k < y + s; k++) {     //y coord loop
                            if (k > gardenHeight) {
                                break;
                            }
                            Spot newS = new Spot(j, k);
                            curPanda.addSpot(newS);
                        }
                    }
                } else {
                    //top left corner x and y coord not in garden
                    for (int j = 0; j < x + s; j++) {     //x coord loop
                        if (j > gardenWidth) {
                            break;
                        }
                        for (int k = 0; k < y + s; k++) {     //y coord loop
                            if (k > gardenHeight) {
                                break;
                            }
                            Spot newS = new Spot(j, k);
                            curPanda.addSpot(newS);
                        }
                    }
                }


                pandaList.set(i, curPanda);

            }


            for (int i = 0; i < pandaList.size(); i++) {

                for (int j= 0; j < pandaList.size(); j++){

                }
            }
            return 0;
        }



        public void addPanda ( int pandaX, int pandaY, int pandaS){
            pandaList.add(new Panda(pandaX, pandaY, pandaS));
        }

        public int getGardenHeight () {
            return gardenHeight;
        }

        public void setGardenHeight ( int gardenHeight){
            this.gardenHeight = gardenHeight;
        }

        public int getGardenWidth () {
            return gardenWidth;
        }

        public void setGardenWidth ( int gardenWidth){
            this.gardenWidth = gardenWidth;
        }

        public int getPandaNum () {
            return pandaNum;
        }

        public void setPandaNum ( int pandaNum){
            this.pandaNum = pandaNum;
        }

        public ArrayList<Panda> getPandaList () {
            return pandaList;
        }

        public void setPandaList (ArrayList < Panda > pandaList) {
            this.pandaList = pandaList;
        }

    }
