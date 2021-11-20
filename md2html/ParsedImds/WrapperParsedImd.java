package md2html.ParsedImds;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import markup.DelimiterDictionary;
import markup.InlineMarkupElement;
import md2html.ParsedInlineMarkdown;
import md2html.tokens.InlineMarkdownToken;

public class WrapperParsedImd extends MotherableParsedImd {
    protected Optional<InlineMarkdownToken> opener = Optional.empty();
    protected Optional<InlineMarkdownToken> closer = Optional.empty();

    public WrapperParsedImd(Optional<MotherableParsedImd> parent, List<ParsedInlineMarkdown> children) {
        super(parent, children);
    }


    @Override
    public InlineMarkupElement toInlineMarkdownElement() {
        assert opener.isPresent();

         // Determine type by opener:
         Class<? extends InlineMarkupElement> elementClass =
             DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.get(opener.get().getText()).markupClass;
         try {
             return ((Class<InlineMarkupElement>)elementClass)
                 .getConstructor(List.class).newInstance(childrenAsImdElements());
         } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
             e.printStackTrace();
             throw new RuntimeException("");
         }
    }
}
