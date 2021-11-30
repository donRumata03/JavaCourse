package markup;

import java.util.List;
import markup.HtmlList;

public class OrderedList extends HtmlList {
    public OrderedList(List<ListItem> items) {
        super(items);
    }

    @Override
    protected String getHtmlTag() {
        return "ol";
    }
}
