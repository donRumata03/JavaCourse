import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;


public class Reverse {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        // Input lines:
        List<List<Integer>> inputData = new ArrayList<List<Integer>>();
        while(inputReader.hasNextLine()) {
            String thisLine = inputReader.nextLine();
            Scanner lineParser = new Scanner(thisLine);
            inputData.add(new ArrayList<Integer>());
            while (lineParser.hasNext()) {
                inputData.get(inputData.size() - 1).add(lineParser.nextInt());
            }
        }

        // Output reversed:
        Collections.reverse(inputData);
        for (List<Integer> line: inputData) {
            Collections.reverse(line);
            ArrayList<String> stringifiedNumbers = new ArrayList<String>();
            for (Integer integer : line) {
                stringifiedNumbers.add(integer.toString());
            }
            System.out.println(String.join(" ", stringifiedNumbers));
        }

//        Functional programming isn't beautiful in java…
//        for (ListIterator line = inputData.listIterator(inputData.size()); line.hasPrevious();) {
//            System.out.println(String.join(" ", line.previous()));
//        }
    }
}
