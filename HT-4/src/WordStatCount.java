import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.IntPredicate;


/*
Fix:
- double try;
- killing JVM (exit)
- errors to System.err
- double memory in some cases => call two variants: fast and without memory overhead
 */

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

class WordWithCount {
    String word;
    int count;

    WordWithCount(String word, int count) {
        this.word = word;
        this.count = count;
    }
}

public class WordStatCount {
    private static LinkedHashMap<String, Integer> constructCounter(BufferedReader buffReader) throws IOException {
        LinkedHashMap<String, Integer> counter = new LinkedHashMap<>();
        WordByWordReader splitter = new WordByWordReader(buffReader);

        while(splitter.hasNextWord()) {
            String thisWord = splitter.nextWord().toLowerCase();
            counter.put(thisWord, counter.getOrDefault(thisWord, 0) + 1);
        }

        return counter;
    }

    private static void writeWordsWithCounts(List<WordWithCount> wordStat, BufferedWriter writer) throws IOException {
        for (WordWithCount wordWithCount: wordStat) {
            writer.write(wordWithCount.word + (" " + wordWithCount.count + "\n"));
        }
    }

    private static List<WordWithCount> dumpCounterToListFast(LinkedHashMap<String, Integer> counter) {
        List<WordWithCount> wordStat = new ArrayList<>();
        counter.forEach((key, value) -> wordStat.add(new WordWithCount(key, value)));
        return wordStat;
    }

    private static List<WordWithCount> dumpCounterToListMemoryEfficient(LinkedHashMap<String, Integer> counter) {
        List<WordWithCount> wordStat = new ArrayList<>();

        while (!counter.isEmpty()) {
            var head = counter.entrySet().iterator();
            var thisEntry = head.next();
            wordStat.add(new WordWithCount(thisEntry.getKey(), thisEntry.getValue()));
            head.remove();
        }
        return wordStat;
    }

    public static void main(String[] args) {
        String inputFilename = args[0];
        String outputFilename = args[1];

        LinkedHashMap<String, Integer> counter = new LinkedHashMap<>(); // accessOrder = false by default

        // Read input file with try-resource idiom:

        try (BufferedReader buffReader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(inputFilename),
                StandardCharsets.UTF_8
            ))
        ) {
            counter = constructCounter(buffReader);
        } catch (FileNotFoundException e) {
            System.err.println("No such file (provided as input): " + inputFilename);
            return;
        } catch (IOException e) {
            System.err.println("There were some errors while working with input file");
        }

        List<WordWithCount> wordStat =
//            dumpCounterToListFast(counter)
            dumpCounterToListMemoryEfficient(counter)
            ;
        // This is stable and O(n log n) in worst case
        Collections.sort(wordStat, Comparator.comparingInt(c -> c.count));

        // Write on output file with try-resource idiom:
        try (BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(outputFilename),
                StandardCharsets.UTF_8
            )
        )) {
            writeWordsWithCounts(wordStat, writer);
        } catch (IOException e) {
            System.err.println("There were some errors while working with output file");
            return;
        }
    }
}
