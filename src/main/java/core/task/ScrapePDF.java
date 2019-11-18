package core.task;

import javafx.concurrent.Task;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class ScrapePDF extends Task<Void> {
    public static void scrape(File path) throws IOException {
        PDDocument doc = PDDocument.load(path);
        int id = doc.getNumberOfPages();
        System.out.println(id);
    }

    @Override
    protected Void call() throws Exception {
        return null;
    }
}
