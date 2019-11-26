package frontend.controller;

import core.api.OpenLibrary;
import core.task.ScrapeISBN;
import javafx.application.Platform;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Component Controller, allocates user actions to events
 */
public class MainMenuController {

    final static private String TAGIDENTIFIER = "tag:";
    // Form components
    @FXML private AnchorPane ap;
    @FXML private Menu actions;
    @FXML public MenuItem addFile;
    @FXML public MenuItem addBibtex;
    @FXML private MenuItem remove;
    @FXML private MenuItem close;
    @FXML private MenuItem about;
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

        addFile.setOnAction(event -> {
                    FileChooser fc = new FileChooser();
                    File file = fc.showOpenDialog(new Stage());

                    ScrapeISBN scrape = new ScrapeISBN(file);
                    final String[] result = new String[1];
                    scrape.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                            new EventHandler<WorkerStateEvent>() {
                                @Override
                                public void handle(WorkerStateEvent event) {
                                    result[0] = scrape.getValue();

                                    // ISBN not found
                                    if(result[0] == null) {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setContentText("No ISBN number found");
                                    }

                                    // perform api lookup
                                    OpenLibrary api = new OpenLibrary(result[0]);

                                }
                            });
                    Platform.runLater(scrape);
                });

        addBibtex.setOnAction(event -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("../form/AddBook.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Bibtex Entry");
                stage.setScene(new Scene(root, 450, 450));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
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

        about.setOnAction(event -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("../form/About.fxml"));
                Stage stage = new Stage();
                stage.setTitle("About");
                stage.setScene(new Scene(root, 200,200));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
