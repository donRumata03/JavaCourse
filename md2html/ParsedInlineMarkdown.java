package md2html;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import markup.DelimiterDictionary;
import markup.InlineMarkupElement;
import markup.MarkupElement;
import markup.Text;
import md2html.InlineMarkdownToken.TokenType;

public class ParsedInlineMarkdown {

    private List<ParsedInlineMarkdown> children;
    private String text = null;
    private Optional<InlineMarkdownToken> opener = Optional.empty();
    private Optional<InlineMarkdownToken> closer = Optional.empty();

    private ParsedInlineMarkdown(List<ParsedInlineMarkdown> children) {
        this.children = children;
    }

    public ParsedInlineMarkdown(List<ParsedInlineMarkdown> children, InlineMarkdownToken opener) {
        this.children = children;
        this.opener = Optional.of(opener);
    }

    public ParsedInlineMarkdown(String text) {
        this.children = null;
        this.text = text;
        this.opener = Optional.of(new InlineMarkdownToken(TokenType.SpecialSymbol, ""));
        this.closer = this.opener;
    }

     public List<InlineMarkupElement> toInlineMarkdownElementList() {
        assert children != null;

        return children.stream().map(ParsedInlineMarkdown::toInlineMarkdownElement).collect(Collectors.toList());
     }

     public InlineMarkupElement toInlineMarkdownElement() {
         assert opener.isPresent();

         if (children == null) {
             return new Text(text);
         }

         // Determine type by opener:
         Class<? extends InlineMarkupElement> elementClass =
             DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.get(opener.get().getText());
         try {
             return ((Class<InlineMarkupElement>)elementClass)
                 .getConstructor(List.class).newInstance(toInlineMarkdownElementList());
         } catch (InstantiationException e) {
             e.printStackTrace();
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         } catch (InvocationTargetException e) {
             e.printStackTrace();
         } catch (NoSuchMethodException e) {
             e.printStackTrace();
         }
         throw new RuntimeException("");
     }

    public static ParsedInlineMarkdown parseString(String source) {
        InlineMarkdownTokenizer tokenizer = new InlineMarkdownTokenizer(source);

        ParsedInlineMarkdown root = new ParsedInlineMarkdown(new ArrayList<>());
        ParsedInlineMarkdown currentNode = root;
        List<ParsedInlineMarkdown> parentSequence = new ArrayList<>();

        // Sequentially process all tokens:
        Optional<InlineMarkdownToken> mayBeNextToken;
        while(true) {
            mayBeNextToken = tokenizer.nextToken();
            if (mayBeNextToken.isEmpty()) {
                break;
            }
            InlineMarkdownToken nextToken = mayBeNextToken.get();

            if (nextToken.getType() == TokenType.Text) {
                currentNode.children.add(new ParsedInlineMarkdown(nextToken.getText()));
            } else {
                if (currentNode.opener.isPresent() && currentNode.opener.get().equals(nextToken)) {
                    // Go to parent
                    currentNode.closer = Optional.of(nextToken);
                    currentNode = parentSequence.get(parentSequence.size() - 1);
                    parentSequence.remove(parentSequence.size() - 1);
                } else {
                    // Init new child:
                    currentNode.children.add(new ParsedInlineMarkdown(new ArrayList<>(), nextToken));
                    parentSequence.add(currentNode);
                    currentNode = currentNode.children.get(currentNode.children.size() - 1);
                }
            }
        }

        // Post-process tree (if there are unclosed elements):
        while (!parentSequence.isEmpty()) {
            if (currentNode.closer.isEmpty()) {
                ParsedInlineMarkdown thisParent = parentSequence.get(parentSequence.size() - 1);
                // Copy elements to parent:

                List<ParsedInlineMarkdown> currentNodeChildren = currentNode.children;
                assert currentNode == thisParent.children.get(thisParent.children.size() - 1); // By address

                thisParent.children.remove(thisParent.children.size() - 1);
                thisParent.children.add(new ParsedInlineMarkdown(currentNode.opener.get().getText()));
                thisParent.children.addAll(currentNodeChildren);
            }

            currentNode = parentSequence.get(parentSequence.size() - 1);
            parentSequence.remove(parentSequence.size() - 1);
        }

        return root;
    }
}
