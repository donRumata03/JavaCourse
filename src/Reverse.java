import bufferedScanning.BufferedScanner;
import bufferedScanning.HexAbcParser;
import bufferedScanning.IntList;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;


public class Reverse {
    public static void main(String[] args) {
        BufferedScanner inputReader = new BufferedScanner(new InputStreamReader(System.in));

        // Input lines:
        List<List<Integer>> inputData = null;
        try {
            inputData = inputNumberStringsOptimized(inputReader);
        } catch (IOException e) {
            e.printStackTrace();
            return;
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
    }

    static List<List<Integer>> inputNumberStringsOptimized(BufferedScanner scanner) throws IOException {
        // Input lines:
        List<List<Integer>> inputData = new ArrayList<>();
        while (true) {
            int newlinesRead = scanner.consumeDelimitersAndNewlines(Character::isWhitespace);

            if (inputData.isEmpty() && newlinesRead != 0) {
                inputData.add(new ArrayList<>());
            }
            for (int i = 0; i < newlinesRead; i++) {
                inputData.add(new ArrayList<>());
//                if (!inputData.isEmpty() && inputData.get(inputData.size() - 1) != null && inputData.get(inputData.size() - 1).isEmpty()) {
//                    inputData.set(inputData.size() - 1, null);
//                }
            }

            if (inputData.isEmpty()) {
                inputData.add(new ArrayList<>());
            }
            String sequenceForInt = scanner.nextSequenceIgnoreEmpty(Character::isWhitespace);
            if (sequenceForInt == null) {
                break;
            }
            inputData.get(inputData.size() - 1).add(HexAbcParser.parseHexAbcInt(sequenceForInt));
        }

        // Last newline:
        int lastInputDataIndex = inputData.size() - 1;
        if (!inputData.isEmpty() && (inputData.get(lastInputDataIndex) == null || inputData.get(lastInputDataIndex).isEmpty())) { // Ignore last newline
            inputData.remove(inputData.size() - 1);
        }

        return inputData;
    }
}
