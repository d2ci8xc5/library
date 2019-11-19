
import core.task.ScrapeISBN;
import javafx.application.Platform;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class TestScrapeISBN {
    private static Logger log = Logger.getLogger("");
    private static String ISBN = "9780062510105";
    private static String PATH = "./src/main/resources/sample.pdf";

    /***
     * Test the retrieval of an ISBN number from a created PDF document
     */
    @Test
    public void scrape() {
        PDDocument tempPDF = new PDDocument();
        try {
            PDPage page = new PDPage();
            tempPDF.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(tempPDF, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.newLineAtOffset(25, 500);
            final String text = ISBN;
            contentStream.showText(text);
            contentStream.endText();
            contentStream.close();
            tempPDF.save(PATH);
            tempPDF.close();
        } catch (IOException e) {
            log.severe("Failed to create sample PDF");
            e.printStackTrace();
            fail();
        }

        ScrapeISBN task = new ScrapeISBN(new File(PATH));
        String output = null;
        task.setOnSucceeded((succeedEvent) -> {
            assertEquals(task.getValue(), "1111");

        });
    }

    /***
     * Delete PDF file after the test has used it
     */
    @After
    public void clean() {
        File samplePDF = new File(PATH);
        samplePDF.delete();
    }
}
