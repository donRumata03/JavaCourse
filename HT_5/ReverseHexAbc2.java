package HT_5;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;



public class ReverseHexAbc2 {
    public static void main(String[] args) {
//        System.out.println(NumberSummator.sumNumbers(12, 13));


        BufferedScanner inputReader = new BufferedScanner(new InputStreamReader(
//            new FileInputStream("sample_test.txt")
            System.in
        ));

        List<IntList> inputData;
        try {
            inputData = inputNumberStrings(inputReader);
        } catch (IOException e) {
            return;
        }

        // Output reversed:
        Collections.reverse(inputData);
        for (IntList line : inputData) {
            if (line != null) {
                for (int i = 0; i < line.size(); i++) {
                    if (i != 0) {
                        System.out.print(" ");
                    }
                    System.out.print(intToAbcString(line.get(line.size() - i - 1)));
                }
            }
            System.out.println();
        }
    }

    static List<IntList> inputNumberStrings(BufferedScanner scanner) throws IOException {
        // Input lines:
        List<IntList> inputData = new ArrayList<>();
        while (true) {
            String thisLine = scanner.nextLine();
            if (thisLine == null) {
                break;
            }
            HexAbcScanner lineParser = new HexAbcScanner(new BufferedScanner(new StringReader(thisLine)));

            inputData.add(new IntList());
            var currentVector = inputData.get(inputData.size() - 1);
            while (true) {
                try {
                    currentVector.add(lineParser.nextInt());
                } catch (NoSuchElementException e) {
                    break;
                }
            }
            if (currentVector.isEmpty()) {
                inputData.set(inputData.size() - 1, null);
            }
        }

        return inputData;
    }

    static String intToAbcString(int number) {
        String regularString = Integer.toString(number);

        StringBuilder resultBuilder = new StringBuilder();

        for (char c: regularString.toCharArray()) {
            resultBuilder.append(Character.isDigit(c) ? (char)(c + 'a' - '0') : c);
        }

        return resultBuilder.toString();
    }
}