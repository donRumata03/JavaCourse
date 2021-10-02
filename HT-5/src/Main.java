import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        Path sample_filename = rootPath.resolve("sample_input_words.txt");

//        Scanner s = new Scanner("");
//        int i = s.nextInt();



        Reader reader;
        try {
            reader = new InputStreamReader(new FileInputStream(sample_filename.toString()), StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        BufferedScanner scanner = new BufferedScanner(reader);

        String nextWord;
        do {
            nextWord = scanner.nextWord();
            System.out.println(nextWord);
        } while (nextWord != null);
    }
}
