import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntPredicate;


class WordByWordReader {
    private BufferedReader buffReader;
    private List<String> tempWords;


    private List<String> splitBy(String stringToSplit, IntPredicate isSpace) {
        List<String> result = new ArrayList<>();

        int n = stringToSplit.length();
        for (int wordStart = 0, wordEnd = 0; wordEnd <= n; wordEnd++) {
            if (wordEnd == n || isSpace.test(stringToSplit.charAt(wordEnd))) {
                if (wordEnd > wordStart) {
                    result.add(stringToSplit.substring(wordStart, wordEnd));
                }
                wordStart = wordEnd + 1;
            }
        }

        return result;
    }

    private void resupplyTemp() throws IOException {
        String nextLine = null;
        while (tempWords.isEmpty()) {
            nextLine = buffReader.readLine();
            if (nextLine == null) {
                return;
            }

            tempWords = splitBy(nextLine, (int boxed) -> {
                char ch = (char)boxed;
                return !(Character.isLetter(ch) || Character.getType(ch) == Character.DASH_PUNCTUATION || ch == '\'');
            }
            );
            Collections.reverse(tempWords);

        }
    }



    WordByWordReader(BufferedReader reader) throws IOException {
        buffReader = reader;
        tempWords = new ArrayList<String>();
        resupplyTemp();
    }

    public boolean hasNextWord() {
        return !tempWords.isEmpty();
    }

    public String nextWord() throws IOException {
        String result = tempWords.get(tempWords.size() - 1);
        tempWords.remove(tempWords.size() - 1);
        resupplyTemp();

        return result;
    }
}


public class WordStatInput {

    private static LinkedHashMap<String, Integer> getCounter(BufferedReader buffReader) throws IOException {
        LinkedHashMap<String, Integer> counter = new LinkedHashMap<>();
        WordByWordReader splitter = new WordByWordReader(buffReader);

        while(splitter.hasNextWord()) {
            String thisWord = splitter.nextWord().toLowerCase();
            counter.put(thisWord, counter.getOrDefault(thisWord, 0) + 1);
        }

        return counter;
    }

    private static void writeCounter(LinkedHashMap<String, Integer> counter,
        BufferedWriter writer) throws IOException {


        counter.forEach((key, value) -> {
            try {
                writer.write(key + (" " + value.toString() + "\n"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public static void main(String[] args) {
        String inputFilename = args[0];
        String outputFilename = args[1];

        LinkedHashMap<String, Integer> counter = new LinkedHashMap<>(); // accessOrder = false by default

        // Read input file with try-resource idiom:
        try {
            BufferedReader buffReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(inputFilename),
                            StandardCharsets.UTF_8
                    )
            );

            try {
                counter = getCounter(buffReader);
            } finally {
                buffReader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No such file (provided as input): " + inputFilename);
            System.exit(1);
        } catch (IOException e) {
            System.out.println("There were some errors while working with input file");
            System.exit(2);
        }

        // Write on output file with try-resource idiom:
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(outputFilename),
                            StandardCharsets.UTF_8
                    )
            );

            try {
                writeCounter(counter, writer);
            } finally {
                writer.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("No such file (provided as input): " + inputFilename);
            System.exit(3);
        } catch (IOException e) {
            System.out.println("There were some errors while working with output file");
            System.exit(4);
        }
    }
}
