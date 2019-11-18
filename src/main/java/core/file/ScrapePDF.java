package core.file;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class ScrapePDF {
    public static void scrape(File path) throws IOException {
        PDDocument doc = PDDocument.load(path);
        Long id = doc.getDocumentId();
        System.out.println(id);
    }
}
