package markup.tests;

import markup.Text;

public class EscapingTest {

    public static void main(String[] args) {
        Text t = new Text("Экранирование должно работать во всех местах: <>&.\n\n");
        var b = new StringBuilder();
        t.toHtml(b);
        System.out.println(b.toString());
    }
}
