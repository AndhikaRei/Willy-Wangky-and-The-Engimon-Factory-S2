package GUI.BattleConfirm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.engimon.Engimon;
import GUI.AlertBox;

public class BattleConfirm {
    // Menampilkan konfirmasi battle
    public static Boolean  display(Engimon ally, Engimon enemy){
        try{
            Stage battleStage = new Stage();
            battleStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader battleLoader = new FXMLLoader(BattleConfirm.class.getResource("BattleConfirm.fxml"));
            AnchorPane root = battleLoader.load();
            BattleConfirmController battleConfirmController = battleLoader.getController();
            battleConfirmController.loadData(ally,enemy);
            Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
            battleStage.setScene(scene);
            battleStage.showAndWait();
            return battleConfirmController.getIsBattle();
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
            return Boolean.FALSE;
        }
    }
}
