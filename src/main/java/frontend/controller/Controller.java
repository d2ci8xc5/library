package frontend.controller;

import core.task.ScrapeISBN;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Component Controller, allocates user actions to events
 */
public class Controller {

    final private String TAGIDENTIFIER = "tag:";
    // Form components
    @FXML private AnchorPane ap;
    @FXML private Menu actions;
    @FXML private MenuItem add;
    @FXML private MenuItem remove;
    @FXML private MenuItem close;
    @FXML private TextField search;
    @FXML private TableView table;
    /**
     * Initialize the scene component actions
     */
    @FXML
    private void initialize() {
        close.setOnAction((event -> {
            Stage stage = (Stage) ap.getScene().getWindow();
            stage.close();
        }));

        add.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(new Stage());
            Platform.runLater(new ScrapeISBN(file));
        });

        search.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER) {
                    String text = search.getText();

                    if(text.contains(TAGIDENTIFIER)) {
                        System.out.println("search by tag");

                        // get all entries in the database by a tag (make this async)?

                        // update gui component
                    } else {
                        System.out.println("search by namsadf");
                        // search database by book name

                    }
                }
            }
        });

    }
}
