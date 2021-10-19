package HT_5;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;


public class ScanTester {
    public static void main(String[] args) {
        Path rootPath =
            // Main.class.getResource("").toString()
            Paths.get(System.getProperty("user.dir"))
        ;
        // System.out.println(rootPath);

        String sampleFilename = "sample_test.txt";
//        String sampleFilename = "sample_input_words.txt";
//        String sampleFilename = "sample_ints.txt";
//        String sampleFilename = "sample_lines.txt";

          Path samplePath = rootPath.resolve("HT_5").resolve("samples").resolve(Paths.get(sampleFilename));

//        Scanner s = new Scanner("");
//        int i = s.nextInt();


        testScanner(samplePath);
//        testBufferizer(samplePath);



    }

    static void testLineSeparators() {
        char[] ca = System.getProperty("line.separator").toCharArray();
        for (char c : ca) {
            System.out.println((int) c);
        }

        int i = 0x000A;

        char[] c = Character.toChars(i);
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
            System.out.println("Initialized;");

            // testScanningWords(scanner);
//            testScanningInts(scanner);
            testScanningLines(scanner);

        } catch (FileNotFoundException e) {
            System.err.println("File doesn't exist: " + path);
            return;
        } catch (IOException e) {
            System.err.println("Error while reading file!");
            return;
        }
    }

    static void testScanningWords(BufferedScanner scanner) throws IOException {
        String nextWord;
        while(true) {
            nextWord = scanner.nextWord();
            if (nextWord == null) {
                break;
            }
            System.out.println("\"" + nextWord + "\"");
        }
    }

    static void testScanningInts(BufferedScanner scanner) throws IOException {
        while(true) {
            try {
                System.out.println("\"" + scanner.nextInt() + "\"");
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }

    static void compareLineReading() {

    }

    static void testScanningLines(BufferedScanner scanner) throws IOException {
        String nextLine;
        while(true) {
            nextLine = scanner.nextLine();
            if (nextLine == null) {
                break;
            }
            System.out.println("\"" + nextLine + "\"");
        }
    }

    static void testBRScanningLines(BufferedReader reader) throws IOException {
        String nextLine;
        while(true) {
            nextLine = reader.readLine();
            if (nextLine == null) {
                break;
            }
            System.out.println("\"" + nextLine + "\"");
        }
    }
}
