package markup;

import java.util.List;
import markup.HtmlList;

public class UnorderedList extends HtmlList {
    public UnorderedList(List<ListItem> items) {
        super(items);
    }

    @Override
    protected String getHtmlTag() {
        return "ul";
    }
}
