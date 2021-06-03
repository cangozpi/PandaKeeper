import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String args[]) {

        boolean isSolutionA = true;

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
            long startTime = System.nanoTime();
            int height = inputParameters.removeFirst();
            int width = inputParameters.removeFirst();
            int numPandas = inputParameters.removeFirst();
            int result = -1;
            Solution sol = new Solution(height, width, numPandas);

            if(isSolutionA== false){
                for (int pandaNo = 0; pandaNo < numPandas; pandaNo++) {
                    int pandaX = inputParameters.removeFirst();
                    int pandaY = inputParameters.removeFirst();
                    int pandaS = inputParameters.removeFirst();
                    sol.addPanda(pandaX, pandaY, pandaS);
                }
                result = sol.solutionB();
            }else{
                result = sol.solutionA();
            }
            System.out.println(result);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;

            System.out.println((totalTime/10^9));

        }


    }
}