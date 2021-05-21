import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class InputParser {

    public InputParser(){}

    public ArrayDeque<Integer> parseInput() throws Exception{


        BufferedReader br = new BufferedReader(new FileReader("Input"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(" ");
                line = br.readLine();

            }
            String everything = sb.toString();
            ArrayDeque<Integer> aa = new ArrayDeque<Integer>() {

            };
            for(String s : everything.split(" ")){
                aa.addLast(Integer.parseInt(s));
            }
            return aa;
        } finally {

            br.close();
        }


    }

}
