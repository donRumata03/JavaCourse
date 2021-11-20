package md2html;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import markup.InlineMarkupElement;
import md2html.ParsedImds.MotherableParsedImd;
import md2html.tokens.InlineMarkdownToken;
import md2html.tokens.TextToken;


public abstract class ParsedInlineMarkdown {
    protected Optional<MotherableParsedImd> parent = Optional.empty();


    protected ParsedInlineMarkdown(MotherableParsedImd parent) {
        this.parent = Optional.of(parent);
    }




     protected abstract List<InlineMarkupElement> childrenAsImdElements();

     public abstract InlineMarkupElement toInlineMarkdownElement();


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
