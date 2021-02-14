package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {


    @FXML
    private TextArea areaText;
    private Stage stage;

    public void init(Stage stage) {
        this.stage = stage;
    }

    //


    /*public void initialize(URL location, ResourceBundle resources)
    {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Text", "*.txt"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));
    }*/
    @FXML
    private void onOpen() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Open New Text");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"));

        File file = fileChooser.showOpenDialog(null);
        FileReader fileReader = new FileReader(file.getAbsolutePath());
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();
        String text;

        while ((text = bufferedReader.readLine()) != null) {
            stringBuilder.append(text).append("\n");
        }

        areaText.setText(stringBuilder.toString());
    }

    @FXML
    private void onSave() throws IOException {
        FileChooser saveChooser = new FileChooser();
        File saveFile = saveChooser.showSaveDialog(stage);
        if (saveFile != null) {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(saveFile));
            try {
                bufferedWriter.write(areaText.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void onSaveAs() {

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save As");
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                PrintWriter savedText = new PrintWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(savedText);
                bufferedWriter.write(areaText.getText());
                bufferedWriter.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onCut() {

    }

    @FXML
    public void onCopy() {
        String text = areaText.getSelectedText();

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        content.putString(text);
        clipboard.setContent(content);
    }

    @FXML
    public void onSelect() {
        
    }

    @FXML
    private void onExit() {
            Platform.exit();
    }


}
