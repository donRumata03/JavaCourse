public class Sum {
    public static void main(String[] args) {
        int sum = 0;

        for (String arg: args) {
            boolean in_number = false;
            int number_start_index = 0;

            for (int i = 0; i <= arg.length(); i++) {
                if (in_number && (i == arg.length() || Character.isWhitespace(arg.charAt(i)))) {
                    // Handle number end:
                    sum += Integer.parseInt(arg.substring(number_start_index, i));

                    in_number = false;
                } else if (!in_number && i != arg.length() && !Character.isWhitespace(arg.charAt(i))) {
                    // Handle number start:
                    in_number = true;
                    number_start_index = i;
                }
            }
        }

        System.out.println(sum);
    }
}
