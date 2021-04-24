package sample.BreedConfirm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.engimon.Engimon;
import sample.AlertBox;

public class BreedConfirm {
    // Menampilkan konfirmasi breeding
    public static Boolean  display(Engimon ally, Engimon enemy){
        try{
            Stage breedingStage = new Stage();
            breedingStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader breedingLoader = new FXMLLoader(sample.BreedConfirm.BreedConfirm.class.getResource("BreedConfirm.fxml"));
            AnchorPane root = breedingLoader.load();
            BreedConfirmController breedConfirmController = breedingLoader.getController();
            breedConfirmController.loadData(ally,enemy);
            Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
            breedingStage.setScene(scene);
            breedingStage.showAndWait();
            return breedConfirmController.getIsBreed();
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
            return Boolean.FALSE;
        }
    }
}
