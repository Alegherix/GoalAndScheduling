package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Styling/main_view.fxml"));
        primaryStage.setTitle("Personligt Schema");
        primaryStage.setScene(new Scene(root, 895, 510));
        primaryStage.getIcons().add(new Image("sample/resources/323-512.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
