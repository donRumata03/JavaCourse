package markup;

import java.util.List;
import markup.tests.HtmlList;

public class UnorderedList extends HtmlList {
    public UnorderedList(List<ListItem> items) {
        super(items);
    }

    @Override
    protected String getHtmlDelimiter() {
        return "ul";
    }
}
