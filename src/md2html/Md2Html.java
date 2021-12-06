package md2html;

import bufferedScanning.BufferedScanner;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

// TODO:
// — At rendering markdown and html: use Dictionary (Map) inside getHtmlTag, getMarkdownOpener, …

/**
 * Converts MarkDown file with filename provided as the first argument to HTML file provided as the second argument
 *
 * Here's a brief specification of supported format:
 * — Text is defined as a sequence of blocks divided by double line breaks.
 * — Block is either a header (if it starts with "#{1-6}\s") or a paragraph (otherwise)
 * — Header just contains text after "#{1-6}\s" in the block
 * — Paragraph is a sequence of inline markup elements ∈ {Text, Emphasis (* or _), Strikeout (--), Strong(** or __), Code(`)}
 * all the delimiters can be opening or closing depending on context
 */
public class Md2Html {

    public static void main(String[] args) {
        String inputPath = args[0];
        String outputPath = args[1];


        ParsedMarkdown parsedMarkdown = new ParsedMarkdown();

        try (MarkdownBlockTokenizer blockTokenizer = new MarkdownBlockTokenizer(
            new BufferedScanner(
                new InputStreamReader(
                    new FileInputStream(inputPath),
                    StandardCharsets.UTF_8
                )
            )
        )) {

            while (true) {
                Optional<String> nextBlock = blockTokenizer.nextBlock();
                if (nextBlock.isEmpty()) {
                    break;
                }
                parsedMarkdown.absorbStringBlock(nextBlock.get());
            }

        } catch (FileNotFoundException e) {
            System.err.println("No such file (provided as input): " + inputPath);
            return;
        } catch (IOException e) {
            System.err.println("There were some errors with filesystem exception");
            return;
        }

        // Write output:
        try (BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(outputPath),
                StandardCharsets.UTF_8
            )
        )) {
            writer.write(parsedMarkdown.toString());
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException appeared => the file exists but is a directory"
                +"rather than a regular file, does not exist but cannot"
                +"be created, or cannot be opened for any other reason");
        } catch (IOException e) {
            System.err.println("There were some errors while working with output file");
        }

    }
}
