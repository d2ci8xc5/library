package core.task;

import javafx.concurrent.Task;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * ScrapeISBN scrapes ISBN number from a PDF document
 */
public class ScrapeISBN extends Task<Void> {
    private File _path;

    public ScrapeISBN(File path) {
        _path = path;
    }

    @Override
    protected Void call() throws Exception {
        // PDF document must not be mutated
        final PDDocument doc = PDDocument.load(_path);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(doc);
        // ISBN regex pattern
        final String pattern = "^(?=(?:\\D*\\d){9}(?:(?:\\D*\\d){3})?$)[\\d-]+$";
        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(text);
        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); ++i) {
                System.out.println(i);
            }
        } else {
            System.out.println("failed");
        }
        return null;
    }
}
