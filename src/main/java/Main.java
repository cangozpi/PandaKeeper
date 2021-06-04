import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    static int SOLUTION_NO = 3;         // solution = 1 distance vector inspired
                                 // solution = 2 dynamic programming
                                 // solution = 3 randomization

    public static void main(String args[]) {



        InputParser parser = new InputParser();
        ArrayDeque<Integer> inputParameters = new ArrayDeque<Integer>();

        try {
            inputParameters = parser.parseInput();
        } catch (Exception e) {
            e.printStackTrace();
        }


        int numPandasTotal = 0;
        int a = inputParameters.removeFirst();
        for (int testCaseNo = 0; testCaseNo < a; testCaseNo++) {   //loops test cas
            Panda.resetPandaNo();
            long startTime = System.nanoTime();
            int height = inputParameters.removeFirst();
            int width = inputParameters.removeFirst();
            int numPandas = inputParameters.removeFirst();
            int result = -1;
            Solution sol = new Solution(height, width, numPandas);
            for (int pandaNo = 0; pandaNo < numPandas; pandaNo++) {
                int pandaX = inputParameters.removeFirst()-1;
                int pandaY = inputParameters.removeFirst()-1;
                int pandaS = inputParameters.removeFirst();
                sol.addPanda(pandaX, pandaY, pandaS);
            }
            switch(SOLUTION_NO){
                case 1:
                    result = sol.solutionA();
                    break;
                case 2:
                    result = sol.solutionC();
                    break;
                case 3:
                    result = sol.solutionD();
                    break;
                default:
                    result = sol.solutionA();
                    break;
            }


            System.out.println("Result = " + result);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;

            System.out.println("Time = " + (totalTime/10^9));

        }


    }
}