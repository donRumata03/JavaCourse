package markup;

import java.util.List;


public abstract class MarkdownWrappingElement implements MarkdownElement {

    protected List<MarkdownWrapableElement> children;



    public MarkdownWrappingElement(List<MarkdownWrapableElement> children) {
        this.children = children;
    }

    protected abstract String getOpener();
    protected abstract String getCloser();

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(getOpener());
        for (MarkdownElement child : children) {
            child.toMarkdown(stringBuilder);
        }
        stringBuilder.append(getCloser());
    }
}
