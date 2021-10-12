import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.IntPredicate;


class WordWithCount {
    String word;
    int count;

    WordWithCount(String word, int count) {
        this.word = word;
        this.count = count;
    }
}

public class WordStatCount {
    public static void main(String[] args) {
        String inputFilename = args[0];
        String outputFilename = args[1];

        LinkedHashMap<String, Integer> counter = new LinkedHashMap<>(); // accessOrder = false by default

        // Read input file with try-resource construction:
        try (InputStreamReader inputFileReader = new InputStreamReader(
            new FileInputStream(inputFilename),
            StandardCharsets.UTF_8
        )
        ) {
            counter = constructCounter(inputFileReader);
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


        // Write on output file with try-resource construction:
        try (BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(outputFilename),
                StandardCharsets.UTF_8
            )
        )) {
            writeWordsWithCounts(wordStat, writer);
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException appeared => the file exists but is a directory"
                +"rather than a regular file, does not exist but cannot"
                +"be created, or cannot be opened for any other reason");
            return;
        } catch (IOException e) {
            System.err.println("There were some errors while working with output file");
            return;
        }
    }


    private static LinkedHashMap<String, Integer> constructCounter(InputStreamReader reader) throws IOException {
        LinkedHashMap<String, Integer> counter = new LinkedHashMap<>();
        BufferedScanner splitter = new BufferedScanner(reader);

        while(true) {
            String thisWord = splitter.nextWord();
            if (thisWord == null){
                break;
            }
            thisWord = thisWord.toLowerCase();
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
}
