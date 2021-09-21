import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        List<String> inputData = new ArrayList<String>();
        while(inputReader.hasNextLine()) {
            inputData.add(inputReader.nextLine());
        }

        Collections.reverse(inputData);
        for (String line: inputData) {
            Scanner lineParser = new Scanner(line);
            List<Integer> thisNumbers = new ArrayList<Integer>();
            while (lineParser.hasNext()) {
                thisNumbers.add(lineParser.nextInt());
            }
            Collections.reverse(thisNumbers);
            ArrayList<String> stringifiedNumbers = new ArrayList<String>();
            for (Integer integer : thisNumbers) {
                stringifiedNumbers.add(integer.toString());
            }
            System.out.println(String.join(" ", stringifiedNumbers));
        }
    }
}
