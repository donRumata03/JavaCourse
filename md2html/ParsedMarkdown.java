package md2html;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import markup.MarkupElement;
import markup.SelfContainedMarkupElement;

public class ParsedMarkdown {
    List<? extends SelfContainedMarkupElement> blockList = new ArrayList<>();

    public void absorbStringBlock(String block) {

    }

    @Override
    public String toString() {
        return blockList.stream()
            .map((Function<SelfContainedMarkupElement, String>) MarkupElement::toHtml)
            .map(Object::toString)
            .collect(Collectors.joining("\n"));
    }
}
