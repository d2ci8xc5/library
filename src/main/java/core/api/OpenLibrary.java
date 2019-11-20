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
        HttpGet httpget = new HttpGet("http://www.google.com/search?q=httpclient&btnG=Google+Search&aq=f&oq=");
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            System.out.println(response.getAllHeaders().toString());
        } finally {
            response.close();
        }
        return new Book();
    }


}
