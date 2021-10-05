import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Path rootPath =
            // Main.class.getResource("").toString()
            Paths.get(System.getProperty("user.dir"))
        ;
        System.out.println(rootPath);
//        Path sampleFilename = rootPath.resolve("simplest_input.txt");
          Path sampleFilename = rootPath.resolve("sample_input_words.txt");

//        Scanner s = new Scanner("");
//        int i = s.nextInt();

        // testScanner(sampleFilename);
        testBufferizer(sampleFilename);
    }

    static void testBufferizer(Path path) {
        try (ReaderBufferizer buff = new ReaderBufferizer(
            new InputStreamReader(
                new FileInputStream(path.toString()), StandardCharsets.UTF_8
            ), 50
        )) {

            while (buff.hasNextChar()) {
                char c = buff.nextChar();
                System.out.println((int)c);
//                System.out.println(" '" + c + "'");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void testScanner(Path path) {
        try (BufferedScanner scanner = new BufferedScanner(
            new InputStreamReader(
                new FileInputStream(path.toString()), StandardCharsets.UTF_8
            )
        )) {
            String nextWord;
            while(true) {
                nextWord = scanner.nextWord();
                if (nextWord == null) {
                    break;
                }
                System.out.println(nextWord);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File doesn't exist: " + path);
            return;
        } catch (IOException e) {
            System.err.println("Error while reading file!");
            return;
        }
    }
}
