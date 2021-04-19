package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;

public class Main extends Application {
    Stage window;
    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Game/Game.fxml"));
        primaryStage.setTitle("Tutturu");
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
