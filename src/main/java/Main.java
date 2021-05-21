public class Main {

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
            long startTime = System.nanoTime();
            int height = inputParameters.removeFirst();
            int width = inputParameters.removeFirst();
            int numPandas = inputParameters.removeFirst();

            Solution sol = new Solution(height, width, numPandas);
            for (int pandaNo = 0; pandaNo < numPandas; pandaNo++) {
                int pandaX = inputParameters.removeFirst();
                int pandaY = inputParameters.removeFirst();
                int pandaS = inputParameters.removeFirst();

                sol.addPanda(pandaX, pandaY, pandaS);


            }
            sol.getPandaList().get(0).resetPandaNo();
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println(totalTime);

        }


    }
}