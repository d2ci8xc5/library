package frontend.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;

public class Controller {
    @FXML AnchorPane ap;
    @FXML Menu actions;
    @FXML MenuItem add;
    @FXML MenuItem remove;
    @FXML MenuItem close;


    @FXML
    private void initialize() {
        close.setOnAction((event -> {
            Stage stage = (Stage) ap.getScene().getWindow();
            stage.close();
        }));

        add.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(new Stage());
        });
    }
}
