package gui.ReplaceSkill;

import javafx.stage.StageStyle;
import gui.AlertBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.engimon.Engimon;

public class ReplaceSkill {
    // Menampilkan konfirmasi breeding
    public static Integer display(Engimon ally){
        try{
            Stage replaceStage = new Stage();
            replaceStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader replaceLoader = new FXMLLoader(ReplaceSkill.class.getResource("ReplaceSkill.fxml"));
            AnchorPane root = replaceLoader.load();
            ReplaceSkillController replaceSkillController = replaceLoader.getController();
            replaceSkillController.loadData(ally);
            Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
            replaceStage.initStyle(StageStyle.UNDECORATED);
            replaceStage.setScene(scene);
            replaceStage.showAndWait();
            if (replaceSkillController.getIsReplace()){
                return replaceSkillController.getIdxReplace();
            } else {
                return null;
            }
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
            return null;
        }
    }
}
