package md2html;

import HT_5.BufferedScanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


// TODO:
// — Multiple MarkDown delimiters for single markup element
// — Rename delimiters to sth mode meaningful

/**
 * Converts MarkDown file with filename provided as the first argument to HTML file provided as the second argument
 *
 * Here's a brief specification of supported format:
 * — Text is defined as a sequence of blocks divided by double line breaks.
 * — Block is either a header (if it starts with "#{1-6}\s") or a paragraph (otherwise)
 * — Header just contains text after "#{1-6}\s" in the block
 * — Paragraph is a sequence of inline markup elements ∈ {Text, Emphasis (* or _), Strikeout (--), Strong(** or __), Code(`)}
 * all the delimiters can be opening or closing depending on context
 */
public class Md2Html {

    public static void main(String[] args) {
        String inputPath = args[0];
        String outputPath = args[1];


        try (MarkdownTokenizer markdownTokenizer = new MarkdownTokenizer(
            new BufferedScanner(
                new InputStreamReader(
                    new FileInputStream(inputPath),
                    StandardCharsets.UTF_8
                )
            )
        )) {

        } catch (FileNotFoundException e) {
            System.err.println("No such file (provided as input): " + inputPath);
            return;
        } catch (IOException e) {
            System.err.println("There were some errors with filesystem exception");
            return;
        }


    }
}
