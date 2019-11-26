package frontend.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jbibtex.BibTeXParser;
import org.jbibtex.ParseException;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Controller for adding books from a bibtex format. Book has ability to be added from
 * bibtex data or manually filling out fields
 */
public class AddBookController {
    @FXML
    AnchorPane ap;
    @FXML
    Button add;
    @FXML
    Button cancel;
    @FXML
    TextField title;
    @FXML
    TextField author;
    @FXML
    TextField path;
    @FXML
    Button pathButton;
    @FXML
    TextArea bibtexTextArea;

    @FXML
    private void initialize() {
        cancel.setOnAction((event -> {
            Stage stage = (Stage) ap.getScene().getWindow();
            stage.close();
        }));

        pathButton.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            File fh = fc.showOpenDialog(new Stage());
            if (fh.isFile()) {
                path.setText(fh.getAbsolutePath());
            }

            // Attempt a parse on file parsing
        });

        bibtexTextArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    String text = bibtexTextArea.getText();
                    //use ByteArrayInputStream to get the bytes of the String and convert them to InputStream.
                    InputStream inputStream = new ByteArrayInputStream(text.getBytes(Charset.forName("UTF-8")));
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    BibTeXParser parser = new BibTeXParser();
                    parser.parse(bufferedReader);
                    System.out.println(parser.getDatabase().getEntries().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });



    }
}

