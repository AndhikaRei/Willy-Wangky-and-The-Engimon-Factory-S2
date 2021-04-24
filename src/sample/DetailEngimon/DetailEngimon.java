package sample.DetailEngimon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.engimon.Engimon;
import sample.AlertBox;

public class DetailEngimon {
    // Menampilkan detail suatu engimon
    public static void  display(Engimon ally){
        try{
            Stage engimonShowcase = new Stage();
            engimonShowcase.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader engimonLoader = new FXMLLoader(DetailEngimon.class.getResource("../DetailEngimon/DetailEngimon.fxml"));
            AnchorPane root = engimonLoader.load();
            DetailEngimonController detailEngimonController = engimonLoader.getController();
            detailEngimonController.loadData(ally);
            Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
            engimonShowcase.setScene(scene);
            engimonShowcase.showAndWait();
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }
}
