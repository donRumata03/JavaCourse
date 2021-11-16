package markup;

import java.util.Collections;
import java.util.List;

public class Insertion extends WrappingMarkupElement implements InlineMarkupElement {

    public Insertion(List<InlineMarkupElement> children) {
        super(Collections.unmodifiableList(children));
    }

    @Override
    protected String getHtmlTag() {
        return "ins";
    }

    @Override
    protected String getMarkdownOpener() {
        return "<<";
    }

    @Override
    protected String getMarkdownCloser() {
        return ">>";
    }
}
