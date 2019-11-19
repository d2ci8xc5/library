package core.task;

import javafx.concurrent.Task;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
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
        // ISBN regex pattern
        final String pattern = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$";
        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(text);
        this.updateValue(matcher.group(0));
        // Return matching string or return null
        //return matcher.find() ? matcher.group(0) : null ;
        if (matcher.find()) {
            this.updateValue(matcher.group(0));
            return matcher.group(0);
        } else {
            return null;
        }
    }

}
