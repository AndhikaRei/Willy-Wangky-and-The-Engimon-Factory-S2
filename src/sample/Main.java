package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    Stage window;
    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Testing");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
//            window = primaryStage;
//            window.setTitle("thenewboston - JavaFX");
//            button = new Button("Click me");
//
//            StackPane layout = new StackPane();
//            layout.getChildren().add(button);
//            Scene scene = new Scene(layout, 300, 250);
//            layout.getChildren();
//            window.setScene(scene);
//            window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
