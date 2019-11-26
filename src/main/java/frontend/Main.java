package frontend;

import core.api.OpenLibrary;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    // Thread pool worker
    public static ExecutorService worker = Executors.newSingleThreadExecutor();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("form/MainMenu.fxml"));
        primaryStage.setTitle("Library");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        OpenLibrary asdf = new OpenLibrary("");
        Platform.runLater(asdf);
        launch(args);
    }
}
