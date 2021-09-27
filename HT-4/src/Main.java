import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class WordByWordReader {
    BufferedReader buffReader;
    List<String> tempWords;


    private List<String> splitBy(String targetString, ) {

    }

    private void resupplyTemp() throws IOException {
        String nextLine = null;
        while (nextLine != null && tempWords.isEmpty()) {
            tempWords = splitBy();
        }
    }



    WordByWordReader(BufferedReader reader) {
        buffReader = reader;
    }

    public boolean hasNextWord() {
        return !tempWords.isEmpty();
    }

    public String nextWord() {
        String result = tempWords.get(tempWords.size() - 1);
        tempWords.remove(tempWords.size() - 1);



        return result;
    }
}


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
