package gui.DetailSkillItem;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.element.Element;
import main.java.inventory.Skill_Item;


public class DetailSkillItemController {
    // Placeholder untuk meletakkan foto skill dari skill item kita
    @FXML private ImageView item_sprite;

    // Info dasar (yang bisa ditulis satu kolom) dari skill item
    // Info nama spesies, nama engimon, dan level engimon
    @FXML private Label item_name;
    @FXML private Label item_desc;
    @FXML private Label item_basePower;
    @FXML private Label item_amount;

    // Info yang perlu ditulis dalam beberapa kolom dari skill item
    // Info dari element
    @FXML private GridPane item_dualInfo;

    // Button
    @FXML private Button btn_exit;

    // Melakukan load data sesuai parameter engimon yang disuplai
    public void loadData(Skill_Item skill_item){

        //  Load image sesuai engimon
        this.item_sprite.setImage(skill_item.getSkill().getIcon(150.0,150.0));

        //  Load info dasar berupa nama spesies, nama engimon, dan level engimon
        this.item_name.setText(skill_item.getSkill().getName());
        this.item_desc.setText(skill_item.getSkill().getDesc());
        this.item_basePower.setText(Integer.toString(skill_item.getSkill().getBasePower()));
        this.item_amount.setText(Integer.toString(skill_item.getAmount()));

        //  Load info panjang

        //  Load element
        int i = 0;
        for(Element el : skill_item.getSkill().getElement()){
            StackPane Npane = new StackPane();
            ImageView element = new ImageView(Element.getSpriteEl(el,25.0,25.0));
            Npane.getChildren().add(element);
            Npane.setAlignment(element, Pos.CENTER);
            this.item_dualInfo.add(Npane,i+1,0);
            i++;
        }

    }
    // Exit command
    public void exit(){
        Stage stage = (Stage) this.btn_exit.getScene().getWindow();
        stage.close();
    }

}
