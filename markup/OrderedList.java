package markup;

import java.util.List;

public class OrderedList extends HtmlList {
    public OrderedList(List<ListItem> items) {
        super(items);
    }

    @Override
    protected String getHtmlDelimiter() {
        return "ol";
    }
}
