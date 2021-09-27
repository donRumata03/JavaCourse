import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.IntPredicate;


class WordByWordReader {
    BufferedReader buffReader;
    List<String> tempWords;


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
        }
    }



    WordByWordReader(BufferedReader reader) throws IOException {
        buffReader = reader;
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


public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello, world!");
        String inputFilename = args[0];
        String outputFilename = args[1];

        try {
            BufferedReader buffReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("input.txt"),
                            StandardCharsets.UTF_8
                    )
            );

            LinkedHashMap<String, Integer> counter = new LinkedHashMap<>();
            try {
                WordByWordReader splitter = new WordByWordReader(buffReader);
            } catch (IOException e) {
                System.out.println("There were some errors while working with file");
            } finally {
                buffReader.close();
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("No such file: " + inputFilename);
        }
    }
}
