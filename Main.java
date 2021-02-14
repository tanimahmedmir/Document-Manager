package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();

        Controller controller = fxmlLoader.getController();
        controller.init(stage);


        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Text Editor");
        stage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
