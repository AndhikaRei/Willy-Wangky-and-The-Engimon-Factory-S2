package sample.DetailSkillItem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.engimon.Engimon;
import main.java.inventory.Skill_Item;
import sample.AlertBox;
import sample.DetailEngimon.DetailEngimonController;

public class DetailSkillItem {
    // Menampilkan detail suatu skillItem
    public static void  display(Skill_Item skill_item){
        try{
            Stage skillItemShowcase = new Stage();
            skillItemShowcase.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader skillItemLoader = new FXMLLoader(DetailSkillItem.class.getResource("../DetailSkillItem/DetailSkillItem.fxml"));
            AnchorPane root = skillItemLoader.load();
            DetailSkillItemController detailSkillItemController = skillItemLoader.getController();
            detailSkillItemController.loadData(skill_item);
            Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
            skillItemShowcase.setScene(scene);
            skillItemShowcase.initStyle(StageStyle.UNDECORATED);
            skillItemShowcase.showAndWait();
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }
}
