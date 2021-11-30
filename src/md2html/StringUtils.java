package md2html;

public class StringUtils {
    public static int findFirstNot(String string, char symbol) {
        int i = 0;
        for (; i < string.length(); i++) {
            if (string.charAt(i) != symbol) {
                break;
            }
        }
        return i;
    }
}
