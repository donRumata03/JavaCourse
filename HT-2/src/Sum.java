public class Sum {
    public static void main(String[] args) {
        int sum = 0;

        for (String next_number_set : args) {
            var builder = new StringBuilder(next_number_set);

            for (int i = 0; i < builder.length(); i++) {
                if (Character.isWhitespace(builder.charAt(i))) {
                    builder.setCharAt(i, ' ');
                }
            }

            for (String s : builder.toString().split(" ")) {
                if (s.isEmpty()) {
                    continue;
                }
                // System.out.println(s);
                sum += Integer.parseInt(s);
            }
        }
        System.out.println(sum);
    }
}

/*
int start_index = 0;
            boolean in_number = false;
            for (int i = 0; i < next_number_set.length; i++) {
                boolean is_space = Character.isWhitespace(next_number_set.charAt(i));

                if () {

                }
            }
 */