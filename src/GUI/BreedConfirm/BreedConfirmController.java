package GUI.BreedConfirm;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.java.battle.Battle;
import main.java.element.Element;
import main.java.engimon.Engimon;
import main.java.skill.Skill;

public class BreedConfirmController {

    // Placeholder untuk meletakkan foto engimon yang akan di breed
    @FXML private ImageView engi1_sprite;
    @FXML private ImageView engi2_sprite;

    // Info dasar (yang bisa ditulis satu kolom) dari engimon yang akan di breed
    // Info nama spesies, nama engimon, dan level engimon
    @FXML private Label engi1_speciesName;
    @FXML private Label engi1_name;
    @FXML private Label engi1_level;
    @FXML private Label engi2_speciesName;
    @FXML private Label engi2_name;
    @FXML private Label engi2_level;

    // Info yang perlu ditulis dalam beberapa kolom engimon kita dan musuh
    // Info dari element
    @FXML private GridPane engi1_dualInfo;
    @FXML private GridPane engi2_dualInfo;
    // Info dari skills
    @FXML private GridPane engi1_skills;
    @FXML private GridPane engi2_skills;

    // Tombol breeding dan cancel
    @FXML private Button button_cancel;
    @FXML private Button button_breed;

    private Boolean isBreeding;

    public Boolean getIsBreed(){
        return this.isBreeding;
    }

    // Melakukan load data sesuai parameter engimon yang disuplai
    public void loadData(Engimon engimon1, Engimon engimon2){
        this.isBreeding = Boolean.FALSE;

        //  Load image sesuai engimon yang akan battle
        //  Engimon `1`
        this.engi1_sprite.setImage(engimon1.getSprite(150,150));
        //  Engimon 2
        this.engi2_sprite.setImage(engimon2.getSprite(150,150));

        //  Load info dasar berupa nama spesies, nama engimon, dan level engimon
        //  Engimon 1
        this.engi1_speciesName.setText(engimon1.getSpecies());
        this.engi1_name.setText(engimon1.getName());
        this.engi1_level.setText(Integer.toString(engimon1.getLevel()));
        //  Engimon 2
        this.engi2_speciesName.setText(engimon2.getSpecies());
        this.engi2_name.setText(engimon2.getName());
        this.engi2_level.setText(Integer.toString(engimon2.getLevel()));

        //  Load info panjang
        //  Load element
        //  Engimon 1
        int i = 0;
        for(Element el : engimon1.getElement()){
            StackPane Npane = new StackPane();
            ImageView element = new ImageView(Element.getSpriteEl(el,25.0,25.0));
            Npane.getChildren().add(element);
            Npane.setAlignment(element, Pos.CENTER);
            this.engi1_dualInfo.add(Npane,i+1,1);
            i++;
        }
        //  Engimon 2
        i = 0;
        for(Element el : engimon2.getElement()){
            StackPane Npane = new StackPane();
            ImageView element = new ImageView(Element.getSpriteEl(el,25.0,25.0));
            Npane.getChildren().add(element);
            Npane.setAlignment(element, Pos.CENTER);
            this.engi2_dualInfo.add(Npane,i+1,1);
            i++;
        }

        //  Load Skill
        i = 0;
        //  Engimon 1
        for(Skill skill : engimon1.getSkill()){
            // Mastery
            StackPane mastery = new StackPane();
            ImageView masterySprite = new ImageView(skill.getSprite(25.0,25.0));
            mastery.getChildren().add(masterySprite);
            mastery.setAlignment(masterySprite, Pos.CENTER);
            this.engi1_skills.add(mastery,0,i);

            // Power Skill
            Label power = new Label(Integer.toString(skill.getBasePower()));
            power.setFont(new Font("Roboto",14));
            this.engi1_skills.add(power,1,i);

            // Nama Skill
            Label name = new Label(skill.getName());
            name.setFont(new Font("Roboto",14));
        this.engi1_skills.add(name,2,i);

            // Element dari skill
            int j = 3;
            for(Element el : skill.getListElement()){
                StackPane element = new StackPane();
                ImageView elementImg = new ImageView(Element.getSpriteEl(el,25.0,25.0));
                element.getChildren().add(elementImg);
                element.setAlignment(elementImg, Pos.CENTER);
                this.engi1_skills.add(element,j,i);
                j++;
            }
            i++;
        }
        i=0;
        //  Engimon 2
        for(Skill skill : engimon2.getSkill()){
            // Mastery
            StackPane mastery = new StackPane();
            ImageView masterySprite = new ImageView(skill.getSprite(25.0,25.0));
            mastery.getChildren().add(masterySprite);
            mastery.setAlignment(masterySprite, Pos.CENTER);
            this.engi2_skills.add(mastery,0,i);

            // Power Skill
            Label power = new Label(Integer.toString(skill.getBasePower()));
            power.setFont(new Font("Roboto",14));
            this.engi2_skills.add(power,1,i);

            // Nama Skill
            Label name = new Label(skill.getName());
            name.setFont(new Font("Roboto",14));
            this.engi2_skills.add(name,2,i);

            // Draw Element
            int j = 3;
            for(Element el : skill.getListElement()){
                StackPane element = new StackPane();
                ImageView elementImg = new ImageView(Element.getSpriteEl(el,25.0,25.0));
                element.getChildren().add(elementImg);
                element.setAlignment(elementImg, Pos.CENTER);
                this.engi2_skills.add(element,j,i);
                j++;
            }
            i++;
        }
    }

    // Apabila tombol breed di tekan
    // Menutup window dan set boolean isBattle menjadi true
    void Breed() {
        this.isBreeding = Boolean.TRUE;
        Stage stage = (Stage) this.button_breed.getScene().getWindow();
        stage.close();

    }

    // Apabila tombol cancel di tekan
    // Menutup window dan set boolean isBattle menjadi true
    void Cancel() {
        this.isBreeding = Boolean.FALSE;
        Stage stage = (Stage) this.button_cancel.getScene().getWindow();
        stage.close();
    }

}
