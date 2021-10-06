import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;


public class ReverseHexAbc2 {
    public static void main(String[] args) throws IOException {
//        test();

        // var b = Integer.parseInt("-4", 16);

        BufferedScanner inputReader = new BufferedScanner(new InputStreamReader(
//            new FileInputStream("sample_test.txt")
            System.in
        ));

        List<IntVector> inputData;
        try {
            inputData = inputNumberStrings(inputReader);
        } catch (IOException e) {
            return;
        }

        // Output reversed:
        Collections.reverse(inputData);
        for (IntVector line : inputData) {
//            System.err.println("line");
            if (line != null) {
                for (int i = 0; i < line.size(); i++) {
                    if (i != 0) {
                        System.out.print(" ");
                    }
                    System.out.print(intToAbcString(line.get(line.size() - i - 1)));
//                System.out.print(line.get(line.size() - i - 1));
                }
            }
            System.out.println();
        }
    }

    static List<IntVector> inputNumberStrings(BufferedScanner scanner) throws IOException {
        // Input lines:
        List<IntVector> inputData = new ArrayList<>();
        while (true) {
            String thisLine = scanner.nextLine();
            if (thisLine == null) {
                // System.err.println("input ended…");
                break;
            }
            HexAbcScanner lineParser = new HexAbcScanner(new BufferedScanner(new StringReader(thisLine)));

            inputData.add(new IntVector());
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

    static void test() {
        Reader r = new InputStreamReader(System.in);
        char[] b = {0, 0};
        int read = 0;
        do {
            try {
                read = r.read(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(read);
        } while(read == 0);
    }
}
