package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Game.GameController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Ambil objek FXML loadernya untuk mengekstrak controller
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("Game/Game.fxml"));
        Parent root = gameLoader.load();
        GameController gameController = gameLoader.getController();

        // Tampilkan Konfirmasi apakah user mau load game atau tidak
        Boolean load = AlertBox.displayConfirmation("Apakah anda mau load game", "Load Game Confirmation");
        try{
            if (load){
                gameController.load();
            }
        }
        catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
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
