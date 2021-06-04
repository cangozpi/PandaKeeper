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


                    HashSet<Panda> neighbours = new HashSet<>();
                    for (Panda panda : spot) {
                        int fedCount = panda.getAlreadyEatenStateCount();
                        if (fedCount < leastFedCount) {
                            leastFedCount = fedCount;
                            leastFedPanda = panda.getPandaNum() - 1;
                            neighbours.add(panda);
                        }
                    }
                    pandaList.get(leastFedPanda).addAlreadyEatenCount();//least eaten panda gets to eat the current state
                    for(Panda panda : spot){
                        panda.addNeighbours(neighbours);
                    }
                }
            }


            //find the max eaten and min eaten spot by a given panda
            int result = 0;
            while (true){
              int maxFed = 0;
              int leastFed = 9999;
                for (Panda panda : pandaList) {
                    int fedCount = panda.getAlreadyEatenStateCount();
                    if (fedCount > maxFed) maxFed = fedCount;
                    if (fedCount < leastFed) {
                        leastFed = fedCount;
                    }
                }
                System.out.println("Max:"+maxFed + " Min:" + leastFed);

                if(result == (maxFed - leastFed)) break;
                result = maxFed -leastFed;
                if(result <= 1)break;

                for (Panda panda: pandaList) {
                    panda.giveBamboo();
                }
            }
        System.out.println("Result:" + result);
        return result;


        /*
        int leastFedPanda = 0;
        int mostFedPanda = 0;
        int maxFed = 0;
        int leastFed = 9999;

        pandaList.sort(new Comparator<Panda>() { //ascending order
            @Override
            public int compare(Panda panda, Panda t1) {
                return panda.getAlreadyEatenStateCount() - t1.getAlreadyEatenStateCount();
            }
        });

       for (Panda panda : pandaList) {
            leastFedPanda = panda.getPandaNum();
            int maxNeighbour = 0;
            int maxNeighbourPanda = 0;
            for(int a : pandaList.get(leastFedPanda).getNeighbours()){
                int ate = pandaList.get(a).getAlreadyEatenStateCount();
                if(ate>maxNeighbour){
                    maxNeighbour = ate;
                    maxNeighbourPanda = a;
                }
            }
            if (maxNeighbour == 0) continue;
            pandaList.get(maxNeighbourPanda).decrementAlreadyEatenCount();
            pandaList.get(leastFedPanda).addAlreadyEatenCount();
        }
*/

            //return the max difference
        }

    public int solutionD() {
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
        while(true){
            if(sortedNonEmptyList.isEmpty()){
                break;
            }
            ArrayList<Panda> spot = sortedNonEmptyList.get(0);
            if(spot.size()==1){
                pandaList.get(spot.get(0).getPandaNum() - 1).addAlreadyEatenCount();
                sortedNonEmptyList.remove(0);
            }else{
                break;
            }
        }

        HashMap<Integer,Integer> results = new HashMap<Integer,Integer>();


        for(int i = 0; i< 100; i++){
            ArrayList<ArrayList<Panda>> sortedNonEmptyListCopy = DeepCopySortedNonEmptyList(sortedNonEmptyList);
            ArrayList<Panda> pandaListCopy = DeepCopyPandaList(pandaList);

             while(!sortedNonEmptyListCopy.isEmpty()){
                        Random rgen = new Random();
                        int r = rgen.nextInt(sortedNonEmptyListCopy.size());
                        ArrayList<Panda> spot = sortedNonEmptyListCopy.remove(r);

                        int leastFedCount = 9999;// 9999 represents pos_infinity
                        int leastFedPanda = 0;

                        for (Panda panda : spot) {
                            int fedCount = pandaListCopy.get(panda.getPandaNum()-1).getAlreadyEatenStateCount();
                            if (fedCount < leastFedCount) {
                                leastFedCount = fedCount;
                                leastFedPanda = panda.getPandaNum() - 1;
                            }
                        }
                        pandaListCopy.get(leastFedPanda).addAlreadyEatenCount();//least eaten panda gets to eat the current state

                    }
                    int maxFed = 0;
                    int leastFed = 9999;
                    for (Panda panda : pandaListCopy) {
                        int fedCount = panda.getAlreadyEatenStateCount();
                        if (fedCount > maxFed) maxFed = fedCount;
                        if (fedCount < leastFed) leastFed = fedCount;
                    }
            int res = maxFed - leastFed;
            if(res <= 1 ){        //already best result so no need to loop
                return res;
            }
            if(results.containsKey(res)){
                results.put(res, results.get(maxFed - leastFed)+1);
            } else{
                results.put(res, 1);
            }



        }


        /*
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

         */

        //find the max eaten and min eaten spot by a given panda

        Iterator iterator = results.entrySet().iterator();
        int maxValue = -1;
        int maxKey = -1;
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            if(maxValue < (int) entry.getValue()){
                maxKey = (int) entry.getKey();
                maxValue = (int) entry.getValue();
            }

        }

        return maxKey;//return the max difference
    }

    public int solutionC() {
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
        while(true){
                    if(sortedNonEmptyList.isEmpty()){
                        break;
                    }
                    ArrayList<Panda> spot = sortedNonEmptyList.get(0);
                    if(spot.size()==1){
                        pandaList.get(spot.get(0).getPandaNum() - 1).addAlreadyEatenCount();
                        sortedNonEmptyList.remove(0);
                    }else{
                        break;
                    }
                }



        ArrayList<ArrayList<Panda>> sortedNonEmptyListCopy = DeepCopySortedNonEmptyList(sortedNonEmptyList);
        ArrayList<Panda> pandaListCopy = DeepCopyPandaList(pandaList);

        //start recursive call
        return solutionCHelper( sortedNonEmptyListCopy, pandaListCopy, -1); //pass -inf and +inf as maxFed and mainFed

    }

    public int solutionCHelper(ArrayList<ArrayList<Panda>> sortedNonEmptyList,ArrayList<Panda> pandaListCopy, int minFed){
        int leastFedCount = 9999;
        int leastFedPanda = 0;

        boolean minFedFlag = true;
        for(Panda p : pandaListCopy){

            int fedCount = p.getAlreadyEatenStateCount();
            if(fedCount == -1){
                minFedFlag = false;
                continue;
            }
            if (fedCount <= leastFedCount) {
                leastFedCount = fedCount;
                leastFedPanda = p.getPandaNum()-1;
            }

        }
        if(minFedFlag){
            minFed = leastFedCount;


        }

        //base case
        if(sortedNonEmptyList.isEmpty()){

            int maxFed = 0;

            for (Panda panda : pandaListCopy) {
                int fedCount = panda.getAlreadyEatenStateCount();
                if (fedCount >= maxFed) maxFed = fedCount;

            }

            return maxFed - minFed;
        }


        int minSol = 99999;
        //for(ArrayList<Panda> spot : sortedNonEmptyList){
        for(int i = 0; i < sortedNonEmptyList.size(); i++){
            ArrayList<Panda> spot = sortedNonEmptyList.get(i);


            int finalLeastFedPanda = leastFedPanda + 1;
            //spot.contains(pandaListCopy.get(leastFedPanda).getPandaNum())
            if(spot.stream().anyMatch(x -> Integer.compare(x.getPandaNum(), finalLeastFedPanda) == 0)){
                //ArrayList<Panda> pandaListCopyCopy = (ArrayList<Panda>) pandaListCopy.clone();
                ArrayList<Panda> pandaListCopyCopy = DeepCopyPandaList(pandaListCopy);

                pandaListCopyCopy.get(leastFedPanda).addAlreadyEatenCount();
                ArrayList<ArrayList<Panda>> sortedNonEmptyListCopy = DeepCopySortedNonEmptyList(sortedNonEmptyList);
                sortedNonEmptyListCopy.remove(i);
                minSol = Math.min(solutionCHelper(sortedNonEmptyListCopy,pandaListCopyCopy, minFed),minSol);
            }
        }
        if(minSol == 99999){
            pandaListCopy.get(leastFedPanda).setAlreadyEatenStateCount(-1);
            minFed = leastFedCount;

            minSol = Math.min(solutionCHelper(sortedNonEmptyList,pandaListCopy, minFed),minSol);
        }
        return minSol;

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

        //helper methods for SolutionC
        public ArrayList<ArrayList<Panda>> DeepCopySortedNonEmptyList(ArrayList<ArrayList<Panda>> sortedNonEmptyList){
            ArrayList<ArrayList<Panda>> cloneList = new ArrayList<ArrayList<Panda>>();
            for(ArrayList<Panda> el : sortedNonEmptyList){
                ArrayList<Panda> newPList = new ArrayList<Panda>();
                for(Panda p: el){
                    Panda pandaClone = new Panda(p.getX(), p.getY(), p.getS(), p.getAlreadyEatenStateCount(), p.getPandaNum());
                    newPList.add(pandaClone);
                }
                cloneList.add(newPList);
            }

            return cloneList;
        }

        public ArrayList<Panda> DeepCopyPandaList(ArrayList<Panda> pandaList){
            ArrayList<Panda> newPandaList = new ArrayList<Panda>();
            for(Panda p: pandaList){
                Panda pandaClone = new Panda(p.getX(), p.getY(), p.getS(), p.getAlreadyEatenStateCount(), p.getPandaNum());
                newPandaList.add(pandaClone);
            }
            return newPandaList;
        }

    }
