package md2html.ParsedImds;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import markup.DelimiterDictionary;
import markup.InlineMarkupElement;
import md2html.tokens.InlineMarkdownToken;
import md2html.tokens.SpecialSymbolToken;

public class WrapperParsedImd extends MotherableParsedImd {
    protected SpecialSymbolToken opener;
    protected Optional<InlineMarkdownToken> closer = Optional.empty();

    public WrapperParsedImd(MotherableParsedImd parent, List<ParsedInlineMarkdown> children, SpecialSymbolToken opener) {
        super(Optional.of(parent), children);
        this.opener = opener;
    }


    @Override
    public InlineMarkupElement toInlineMarkdownElement() {
         // Determine type by opener:
//         Class<? extends InlineMarkupElement> elementClass =
//             DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.get(opener.getText()).markupClass;
//         try {
//             return ((Class<InlineMarkupElement>)elementClass)
//                 .getConstructor(List.class).newInstance(childrenAsImdElements());
//         } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//             e.printStackTrace();
//             throw new RuntimeException("");
//         }

        return DelimiterDictionary.instantiateImdByDelimiter(opener.getText(), childrenAsImdElements());
    }
}
