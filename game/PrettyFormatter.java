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



        Arrays.stream(table).map((T[] line) ->
            String.format(formattingString, (Stream::of() Arrays.stream(line)).map(Object::toString).toArray())
        ).collect(Collectors.joining("\n"));

        for (T[] ts : table) {
            builder.append(String.format(formattingString,
                Arrays.stream(ts).map(T::toString).toArray()
            )).append("\n");
        }

        return builder.toString();
    }
}
