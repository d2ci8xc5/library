package core.task;

import javafx.concurrent.Task;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * ScrapeISBN scrapes ISBN number from a PDF document
 */
public class ScrapeISBN extends Task<String> {
    private File _path;
    public ScrapeISBN(File path) {
        _path = path;
    }

    @Override
    protected String call() throws Exception {
        // PDF document must not be mutated
        final PDDocument doc = PDDocument.load(_path);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(doc);
        doc.close();
        System.out.println(text);
        // ISBN regex pattern
        final String pattern = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(text);
        // Return matching string or return null
        if (matcher.find()) {
            String result = new String(matcher.group(0));
            updateValue(result);
            return result;
        } else {
            return null;
        }
    }

}
