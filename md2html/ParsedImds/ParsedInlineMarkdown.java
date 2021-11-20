package md2html.ParsedImds;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import markup.DelimiterDictionary.OpenCloseness;
import markup.InlineMarkupElement;
import markup.WrappingMarkupElement;
import md2html.InlineMarkdownTokenizer;
import md2html.tokens.InlineMarkdownToken;
import md2html.tokens.SpecialSymbolToken;
import md2html.tokens.TextToken;


public abstract class ParsedInlineMarkdown {
    protected Optional<MotherableParsedImd> parent;


    protected ParsedInlineMarkdown(Optional<MotherableParsedImd> parent) {
        this.parent = parent;
    }


     public abstract List<InlineMarkupElement> childrenAsImdElements();

     public abstract InlineMarkupElement toInlineMarkdownElement();


    public static ParsedInlineMarkdown parseString(String source) {
        InlineMarkdownTokenizer tokenizer = new InlineMarkdownTokenizer(source);

        ParsedInlineMarkdown root = new RootParsedImd();
        MotherableParsedImd currentNode = (MotherableParsedImd) root;
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
                currentNode.children.add(new TextParsedImd(currentNode, nextToken.getText()));
            } else {
                if (
                    currentNode instanceof WrapperParsedImd &&
                        ((SpecialSymbolToken)nextToken).canClose(((WrapperParsedImd)currentNode).opener)
                ) {
                    // «Close» this Node and go to parent:
                    assert ((SpecialSymbolToken)nextToken).getOpenCloseness() != OpenCloseness.Opening;

                    ((WrapperParsedImd) currentNode).closer = Optional.of(nextToken);

                    assert currentNode.parent.isPresent();
                    currentNode = currentNode.parent.get();
                } else {
                    // «Open» new child and go to it:
                    assert ((SpecialSymbolToken)nextToken).getOpenCloseness() != OpenCloseness.Closing;

                    currentNode.children.add(new WrapperParsedImd(currentNode, new ArrayList<>(), (SpecialSymbolToken) nextToken));
                    currentNode = (MotherableParsedImd) currentNode.children.get(currentNode.children.size() - 1);
                }
            }
        }

        // Post-process tree (if there are unclosed elements):
        while (currentNode.parent.isPresent()) {
            assert currentNode instanceof WrapperParsedImd;
            if (((WrapperParsedImd)currentNode).closer.isEmpty()) {
                MotherableParsedImd thisParent = currentNode.parent.get();
                // Fictively copy elements to parent:

                List<ParsedInlineMarkdown> currentNodeChildren = currentNode.children;
                assert currentNode == thisParent.children.get(thisParent.children.size() - 1); // Compare by address

                thisParent.children.remove(thisParent.children.size() - 1);

                thisParent.children.add(new TextParsedImd(thisParent, ((WrapperParsedImd) currentNode).opener.getText()));
                thisParent.children.add(new GrouperParsedImd(thisParent, currentNodeChildren));
            }

            currentNode = currentNode.parent.get();
        }
        assert currentNode == root;

        return root;
    }
}
