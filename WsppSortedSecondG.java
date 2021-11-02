import HT_5.BufferedScanner;
import HT_5.IntList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;



class SingleWordInfo {
    int count = 0;
    IntList indexes = new IntList();
}

class WordWithInfo {
    // This fields aren't private because doesn't assume any behaviour itself
    // It would be more appropriate to call it a `struct`.
    // See C++ Core Guidelines:
    // https://isocpp.github.io/CppCoreGuidelines/CppCoreGuidelines#c2-use-class-if-the-class-has-an-invariant-use-struct-if-the-data-members-can-vary-independently
    // And the constructor should be generated automatically…
    String word;
    SingleWordInfo info;

    public WordWithInfo(String word, SingleWordInfo info) {
        this.word = word;
        this.info = info;
    }
}

public class WsppSortedSecondG {
    public static void main(String[] args) {
        String inputFilename = args[0];
        String outputFilename = args[1];

        LinkedHashMap<String, SingleWordInfo> counter = new LinkedHashMap<>(); // accessOrder = false by default

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
            System.err.println("There were some errors with filesystem exception");
            return;
        }

        List<WordWithInfo> wordStat = dumpCounterToListMemoryEfficient(counter);

        // This is stable and O(n log n) in worst case
        Collections.sort(wordStat, Comparator.comparing(c -> c.word));


        // Write on output file with try-resource construction:
        try (BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(outputFilename),
                StandardCharsets.UTF_8
            )
        )) {
            writeWordStat(wordStat, writer);
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException appeared => the file exists but is a directory"
                +"rather than a regular file, does not exist but cannot"
                +"be created, or cannot be opened for any other reason");
        } catch (IOException e) {
            System.err.println("There were some errors while working with output file");
        }
    }


    private static LinkedHashMap<String, SingleWordInfo> constructCounter(InputStreamReader reader) throws IOException {
        LinkedHashMap<String, SingleWordInfo> counter = new LinkedHashMap<>();
        BufferedScanner scanner = new BufferedScanner(reader);

        int totalIndex = 0;

        while(true) {
            String thisLine = scanner.nextLine();
            if (thisLine == null) {
                break;
            }

            BufferedScanner lineSplitter = new BufferedScanner(new StringReader(thisLine));
            HashMap<String, Integer> wordCountInThisLine = new HashMap<>();

            while(true) {
                String thisWord = lineSplitter.nextWord();
                if (thisWord == null) {
                    break;
                }
                totalIndex++;
                thisWord = thisWord.toLowerCase();
                wordCountInThisLine.compute(thisWord, (word, count) -> (count == null) ? 1 : count + 1);
                Integer indexInThisLine = wordCountInThisLine.get(thisWord);

                int finalTotalIndex = totalIndex;
                counter.compute(thisWord, (word, wordInfo) -> {
                    if (wordInfo == null) {
                        wordInfo = new SingleWordInfo();
                    }
                    wordInfo.count++;

                    if (indexInThisLine % 2 == 0) {
                        wordInfo.indexes.add(finalTotalIndex);
                    }
                    return wordInfo;
                });
            }
        }

        return counter;
    }

    private static List<WordWithInfo> dumpCounterToListMemoryEfficient(LinkedHashMap<String, SingleWordInfo> counter) {
        List<WordWithInfo> wordStat = new ArrayList<>();

        while (!counter.isEmpty()) {
            var head = counter.entrySet().iterator();
            var thisEntry = head.next();
            wordStat.add(new WordWithInfo(thisEntry.getKey(), thisEntry.getValue()));
            head.remove();
        }
        return wordStat;
    }


    private static void writeWordStat(List<WordWithInfo> wordStat, BufferedWriter writer) throws IOException {
        for (var wordData: wordStat) {
            StringBuilder lineBuilder = new StringBuilder();
            lineBuilder.append(wordData.word);
            lineBuilder.append(' ');
            lineBuilder.append(wordData.info.count);

            for (int i = 0; i < wordData.info.indexes.size(); i++) {
                lineBuilder.append(' ');
                lineBuilder.append(wordData.info.indexes.get(i));
            }
            lineBuilder.append("\n");

            writer.write(lineBuilder.toString());
        }
    }
}
