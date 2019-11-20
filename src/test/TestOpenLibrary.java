import core.api.OpenLibrary;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.junit.Test;

public class TestOpenLibrary {
    private static String ISBN = "9780062510105";

    @Test
    public void testOpenLibraryISBNLookup() {
        OpenLibrary openLibrary = new OpenLibrary(ISBN);
        openLibrary.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {

                    }
                });
        Thread thr = new Thread(openLibrary);
        thr.start();
        try {
            thr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
