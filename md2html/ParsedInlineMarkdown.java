package md2html;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import markup.InlineMarkupElement;
import markup.MarkupElement;
import markup.Text;
import md2html.InlineMarkdownToken.TokenType;

public class ParsedInlineMarkdown {

    private List<ParsedInlineMarkdown> children;
    private Optional<InlineMarkdownToken> opener = Optional.empty();
    private Optional<InlineMarkdownToken> closer = Optional.empty();

    private ParsedInlineMarkdown(List<ParsedInlineMarkdown> children) {
        this.children = children;
    }

    public ParsedInlineMarkdown(List<ParsedInlineMarkdown> children, InlineMarkdownToken opener) {
        this.children = children;
        this.opener = Optional.of(opener);
    }

    // public to

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
                currentNode.children.add(new Text(nextToken.getText()));
            } else {
                if (currentNode.opener.isPresent() && currentNode.opener.get().equals(nextToken)) {
                    // Go to parent
                    currentNode.closer = Optional.of(nextToken);
                    currentNode = parentSequence.get(parentSequence.size() - 1);
                    parentSequence.remove(parentSequence.size() - 1);
                } else if () {
                    // Last element is fictive:

                }
                else {
                    // Init new child:
                    currentNode.children.add(new ParsedInlineMarkdown(new ArrayList<>(), nextToken));
                }
            }
        }

        return root;
    }
}
