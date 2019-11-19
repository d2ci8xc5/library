package frontend;

import core.task.ScrapeISBN;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("form/sample.fxml"));
        primaryStage.setTitle("library");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ScrapeISBN isbn = new ScrapeISBN(new File("./sample.pdf"));
        isbn.run();
        launch(args);
    }
}
