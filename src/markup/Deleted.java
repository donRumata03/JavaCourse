package markup;

import java.util.Collections;
import java.util.List;

public class Deleted extends WrappingMarkupElement implements InlineMarkupElement {

    public Deleted(List<InlineMarkupElement> children) {
        super(Collections.unmodifiableList(children));
    }

    @Override
    protected String getHtmlTag() {
        return "del";
    }

    @Override
    protected String getMarkdownOpener() {
        return "}}";
    }

    @Override
    protected String getMarkdownCloser() {
        return "{{";
    }
}
