public class Sum {
    public static void main(String[] args) {
        System.err.println("Hello, world!");

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
                // System.err.println(s);
                sum += Integer.parseInt(s);
            }
        }
        System.out.println(sum);
    }
}
