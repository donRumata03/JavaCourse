package HT_6; // Comment when passing tests

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;


import HT_5.BufferedScanner;
import HT_5.IntList;



public class Wspp {
    public static void main(String[] args) {
        String inputFilename = args[0];
        String outputFilename = args[1];

        LinkedHashMap<String, IntList> wordStat = new LinkedHashMap<>(); // accessOrder = false by default

        // Read input file with try-resource construction:
        try (InputStreamReader inputFileReader = new InputStreamReader(
            new FileInputStream(inputFilename),
            StandardCharsets.UTF_8
        )
        ) {
            wordStat = constructCounter(inputFileReader);
        } catch (FileNotFoundException e) {
            System.err.println("No such file (provided as input): " + inputFilename);
            return;
        } catch (IOException e) {
            System.err.println("There were some errors while working with input file");
            return;
        }

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


    private static LinkedHashMap<String, IntList> constructCounter(InputStreamReader reader) throws IOException {
        LinkedHashMap<String, IntList> wordStat = new LinkedHashMap<>();
        BufferedScanner splitter = new BufferedScanner(reader); // Not supposed to throw exceptions assumed to be called

        for(int wordIndex = 1; ; wordIndex++) {
            String thisWord = splitter.nextWord();
            if (thisWord == null){
                break;
            }
            thisWord = thisWord.toLowerCase();

//          wordStat.put(thisWord, wordStat.getOrDefault(thisWord, new IntList()).add(1)); <-
            // Creates a list every time but is performs only one search in hashMap, which is more important
            // Unfortunately, Java seems to have no entry API…
            wordStat.put(thisWord, wordStat.getOrDefault(thisWord, new IntList()).add(wordIndex));
        }

        return wordStat;
    }

    private static void writeWordStat(LinkedHashMap<String, IntList> wordStat, BufferedWriter writer) throws IOException {

        for (var entry: wordStat.entrySet()) {
            // Some dirty imperative string building:
            String word = entry.getKey();
            IntList wordIndexes = entry.getValue();

            StringBuilder lineBuilder = new StringBuilder(word);
            lineBuilder.append(" ");
            lineBuilder.append(wordIndexes.size());

            for (int i = 0; i < wordIndexes.size(); i++) {
                lineBuilder.append(" ");
                lineBuilder.append(wordIndexes.get(i));
            }

            lineBuilder.append("\n");
            writer.write(lineBuilder.toString());
        }
    }
}


