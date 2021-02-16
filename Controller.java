package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Controller {


    @FXML
    private TextArea areaText;
    private Stage stage;
    public String path;

    public void init(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onOpen() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Open New Text");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"));

        File file = fileChooser.showOpenDialog(null);

        FileReader fileReader = new FileReader(file.getAbsolutePath());
        path = file.getAbsolutePath();
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();
        String text;

        while ((text = bufferedReader.readLine()) != null) {
            stringBuilder.append(text).append("\n");
        }

        areaText.setText(stringBuilder.toString());
    }

    @FXML
    public void onSave() throws IOException {
        File file = new File(path);
        PrintWriter savedText = new PrintWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(savedText);
        bufferedWriter.write(areaText.getText());
        bufferedWriter.close();
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
    public void onSelectAll() {
        areaText.selectAll();
    }

    @FXML
    public void onCut() {
        String text = areaText.getSelectedText();

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        content.putString(text);
        clipboard.setContent(content);

        areaText.setText(areaText.getText().replace(areaText.getSelectedText(),""));

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
    public void onPaste() {
        Clipboard systemClipboard = Clipboard.getSystemClipboard();
        String text = systemClipboard.getString();
        int caretPosition = areaText.getCaretPosition();
        areaText.insertText(caretPosition, text);
    }

    @FXML
    public void onDelete() {
        areaText.setText(areaText.getText().replace(areaText.getSelectedText(),""));
    }

    @FXML
    private void onExit() {
            Platform.exit();
    }


}
