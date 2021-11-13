package markup;

import java.util.List;

public class UnorderedList extends HtmlList {
    public UnorderedList(List<ListItem> items) {
        super(items);
    }

    @Override
    protected String getHtmlDelimiter() {
        return "ul";
    }
}
