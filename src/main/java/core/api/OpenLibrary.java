package core.api;
import core.Book;
import javafx.concurrent.Task;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class OpenLibrary extends Task<Book> {

    private String _isbn;
    public OpenLibrary(String isbn) {
       _isbn = isbn;
    }
    @Override
    protected Book call() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://openlibrary.org/api/books?bibkeys=ISBN:0201558025,LCCN:93005405");
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            System.out.println(response.toString());
        } finally {
            response.close();
        }
        return new Book();
    }
}
