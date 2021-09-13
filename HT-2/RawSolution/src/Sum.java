public class Sum {
    public static void main(String[] args) {
        int totalSum = 0;

        for (String arg: args) {
            boolean inNumber = false;
            int numberStartIndex = 0;

            for (int charIndex = 0; charIndex <= arg.length(); charIndex++) {
                if (inNumber && (charIndex == arg.length() || Character.isWhitespace(arg.charAt(charIndex)))) {
                    // Handle number end:
                    totalSum += Integer.parseInt(arg.substring(numberStartIndex, charIndex));

                    inNumber = false;
                } else if (!inNumber && charIndex != arg.length() && !Character.isWhitespace(arg.charAt(charIndex))) {
                    // Handle number start:
                    inNumber = true;
                    numberStartIndex = charIndex;
                }
            }
        }

        System.out.println(totalSum);
    }
}
