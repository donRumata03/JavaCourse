package game;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrettyFormatter {
    static <T> String formatNumberedTable(T[][] table) {
        int rows = table.length;
        int cols = table[0].length;


        // Determine max number width:
        int maxWidth = Integer.max(
            Integer.toString(rows).length(),
            Integer.toString(cols).length()
        );
        StringBuilder builder = new StringBuilder();

        String formattingString = ("%" + (maxWidth + 1) + "s").repeat(cols + 1);

        String[] forFirstLine = (String[]) IntStream.range(0, cols + 1).mapToObj(i -> (i == 0) ? "" : Integer.toString(i)).toArray();
        builder.append(String.format(formattingString, (Object[]) forFirstLine));

        for (int i = 0; i < table.length; i++) {
            T[] ts = table[i];
            Stream<String> lineNumberStream = Stream.of(Integer.toString(i + 1));
            builder.append(String.format(formattingString,
                Stream.concat(lineNumberStream, Arrays.stream(ts).map(T::toString)).toArray()
            ));
            if (i != table.length - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }
}
