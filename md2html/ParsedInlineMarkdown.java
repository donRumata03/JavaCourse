package md2html;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import markup.DelimiterDictionary;
import markup.InlineMarkdownGrouper;
import markup.InlineMarkupElement;
import markup.Text;
import md2html.tokens.InlineMarkdownToken;
import md2html.tokens.SpecialSymbolToken;
import md2html.tokens.TextToken;


public class ParsedInlineMarkdown {

    private ParsedInlineMarkdown parent = null;
    private List<ParsedInlineMarkdown> children = null;
    private String text = null;
    private Optional<InlineMarkdownToken> opener = Optional.empty();
    private Optional<InlineMarkdownToken> closer = Optional.empty();

    private ParsedInlineMarkdown(ParsedInlineMarkdown parent, List<ParsedInlineMarkdown> children) {
        this.parent = parent;
        this.children = children;
    }

    public ParsedInlineMarkdown(ParsedInlineMarkdown parent, List<ParsedInlineMarkdown> children, InlineMarkdownToken opener) {
        this.parent = parent;
        this.children = children;
        this.opener = Optional.of(opener);
    }

    public ParsedInlineMarkdown(ParsedInlineMarkdown parent, String text) {
        // Text
    }

     public List<InlineMarkupElement> toInlineMarkdownElementList() {
        assert children != null;

        return children.stream().map(ParsedInlineMarkdown::toInlineMarkdownElement).collect(Collectors.toList());
     }

     public InlineMarkupElement toInlineMarkdownElement() {

        ///// TODO: SPLIT BY CLASSES

        //         if (parent == null) {
//             throw new IllegalArgumentException("`this` shouldn't be root");
//         }
//
//         if (children == null) {
//             return new Text(text);
//         } else if (opener.isEmpty()) {
//             // It's just a grouper:
//             return new InlineMarkdownGrouper(toInlineMarkdownElementList());
//         }
//
//         // Determine type by opener:
//         Class<? extends InlineMarkupElement> elementClass =
//             DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.get(opener.get().getText());
//         try {
//             return ((Class<InlineMarkupElement>)elementClass)
//                 .getConstructor(List.class).newInstance(toInlineMarkdownElementList());
//         } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//             e.printStackTrace();
//             throw new RuntimeException("");
//         }
     }

    public static ParsedInlineMarkdown parseString(String source) {
        InlineMarkdownTokenizer tokenizer = new InlineMarkdownTokenizer(source);

        ParsedInlineMarkdown root = new ParsedInlineMarkdown(null, new ArrayList<>());
        ParsedInlineMarkdown currentNode = root;
//        List<ParsedInlineMarkdown> parentSequence = new ArrayList<>();

        // Sequentially process all tokens:
        Optional<InlineMarkdownToken> mayBeNextToken;
        while(true) {
            mayBeNextToken = tokenizer.nextToken();
            if (mayBeNextToken.isEmpty()) {
                break;
            }
            InlineMarkdownToken nextToken = mayBeNextToken.get();

            if (nextToken instanceof TextToken) {
                currentNode.children.add(new ParsedInlineMarkdown(currentNode, nextToken.getText()));
            } else {
                if (currentNode.opener.isPresent() && currentNode.opener.get().equals(nextToken)) {
                    // Go to parent
                    currentNode.closer = Optional.of(nextToken);
                    currentNode = currentNode.parent;
                } else {
                    // Init new child:
                    currentNode.children.add(new ParsedInlineMarkdown(currentNode, new ArrayList<>(), nextToken));
                    currentNode = currentNode.children.get(currentNode.children.size() - 1);
                }
            }
        }

        // Post-process tree (if there are unclosed elements):
        while (currentNode.parent != null) {
            if (currentNode.closer.isEmpty()) {
                ParsedInlineMarkdown thisParent = currentNode.parent;
                // Copy elements to parent:

                List<ParsedInlineMarkdown> currentNodeChildren = currentNode.children;
                assert currentNode == thisParent.children.get(thisParent.children.size() - 1); // By address

                thisParent.children.remove(thisParent.children.size() - 1);

                assert currentNode.opener.isPresent();
                thisParent.children.add(new ParsedInlineMarkdown(thisParent, currentNode.opener.get().getText()));
                thisParent.children.add(new ParsedInlineMarkdown(thisParent, currentNodeChildren));
            }

            currentNode = currentNode.parent;
        }
        assert currentNode == root;

        return root;
    }
}
