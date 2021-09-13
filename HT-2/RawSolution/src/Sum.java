public class Sum {
    public static void main(String[] args) {
        int totalSum = 0;

        for (String arg: args) {
            boolean inNumber = false;
            int numberStartIndex = 0;

            for (int char_index = 0; char_index <= arg.length(); char_index++) {
                if (inNumber && (char_index == arg.length() || Character.isWhitespace(arg.charAt(char_index)))) {
                    // Handle number end:
                    totalSum += Integer.parseInt(arg.substring(numberStartIndex, char_index));

                    inNumber = false;
                } else if (!inNumber && char_index != arg.length() && !Character.isWhitespace(arg.charAt(char_index))) {
                    // Handle number start:
                    inNumber = true;
                    numberStartIndex = char_index;
                }
            }
        }

        System.out.println(totalSum);
    }
}
