package markup.tests;

import java.util.Map;
import markup.ListTest;
import markup.OrderedList;
import markup.Paragraph;
import markup.UnorderedList;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HtmlListTest extends ListTest {
    private static final Map<String, String> HTML = Map.of();

    @Override
    protected void test(final Paragraph paragraph, final String expected) {
        test(paragraph::toHtml, expected, HTML);
    }

    @Override
    protected void test(final UnorderedList list, final String expected) {
        test(list::toHtml, expected, HTML);
    }

    @Override
    protected void test(final OrderedList list, final String expected) {
        test(list::toHtml, expected, HTML);
    }

    public static void main(final String[] args) {
        new HtmlListTest().run();
    }
}