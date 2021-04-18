package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.java.element.Element;
import main.java.engimon.Engimon;
import main.java.map.Map;
import main.java.battle.*;
import main.java.skill.Skill;


public class BattleConfirmController {
    // Placeholder untuk meletakkan foto engimon kita dan musuh
    @FXML private ImageView ally_sprite;
    @FXML private ImageView enemy_sprite;

    // Text berisikan power dari engimon kita dan musuh
    @FXML private Label ally_power;
    @FXML private Label enemy_power;

    // Info dasar (yang bisa ditulis satu kolom) dari engimon kita dan musuh
    // Info nama spesies, nama engimon, dan level engimon
    @FXML private Label ally_speciesName;
    @FXML private Label ally_name;
    @FXML private Label ally_level;
    @FXML private Label enemy_speciesName;
    @FXML private Label enemy_name;
    @FXML private Label enemy_level;

    // Info yang perlu ditulis dalam beberapa kolom engimon kita dan musuh
    // Info dari element dan darah engimon
    @FXML private GridPane ally_dualInfo;
    @FXML private GridPane enemy_dualInfo;
    // Info dari skill
    @FXML private GridPane ally_skills;
    @FXML private GridPane enemy_skills;

    // Tombol battle dan tombol run
    @FXML Button button_battle;
    @FXML Button button_run;

    // Boolean yang menyatakan player run atau tidak
    private Boolean isBattle;

    // getter
    public Boolean getIsBattle(){
        return this.isBattle;
    }

    // Melakukan load data sesuai parameter engimon yang disuplai
    public void loadData(Engimon ally, Engimon enemy){
        this.isBattle = Boolean.FALSE;

        //  Load image sesuai engimon yang akan battle
        //  Engimon kita
        this.ally_sprite.setImage(ally.getSprite(150,150));
        //  Engimon musuh
        this.enemy_sprite.setImage(enemy.getSprite(150,150));

        //  Load power
        //  Engimon kita
        this.ally_power.setText(Double.toString(battle.totalPower(ally,enemy)));
        //  Engimon musuh
        this.enemy_power.setText(Double.toString(battle.totalPower(ally,enemy)));

        //  Load info dasar berupa nama spesies, nama engimon, dan level engimon
        //  Engimon kita
        this.ally_speciesName.setText(ally.getSpecies());
        this.ally_name.setText(ally.getName());
        this.ally_level.setText(Integer.toString(ally.getLevel()));
        //  Engimon musuh
        this.enemy_speciesName.setText(enemy.getSpecies());
        this.enemy_name.setText(enemy.getName());
        this.enemy_level.setText(Integer.toString(enemy.getLevel()));

        //  Load info panjang
        //  Info live
        //  Engimon kita
        Image heart = new Image("./main/resources/heart.png",20,20,false,false);
        for (int i =0; i < ally.getLives(); i++){
            StackPane Npane = new StackPane();
            ImageView heartView = new ImageView(heart);
            Npane.getChildren().add(heartView);
            Npane.setAlignment(heartView, Pos.CENTER);
            this.ally_dualInfo.add(Npane,i+1,0);
        }
        //  Engimon musuh
        for (int i =0; i < enemy.getLives(); i++){
            StackPane Npane = new StackPane();
            ImageView heartView = new ImageView(heart);
            Npane.getChildren().add(heartView);
            Npane.setAlignment(heartView, Pos.CENTER);
            this.enemy_dualInfo.add(Npane,i+1,0);
        }
        //  Load element
        //  Engimon kita
        int i = 0;
        for(Element el : ally.getElement()){
            StackPane Npane = new StackPane();
            ImageView element = new ImageView(Element.getSpriteEl(el,25.0,25.0));
            Npane.getChildren().add(element);
            Npane.setAlignment(element, Pos.CENTER);
            this.ally_dualInfo.add(Npane,i+1,1);
            i++;
        }
        //  Engimon musuh
        i = 0;
        for(Element el : enemy.getElement()){
            StackPane Npane = new StackPane();
            ImageView element = new ImageView(Element.getSpriteEl(el,25.0,25.0));
            Npane.getChildren().add(element);
            Npane.setAlignment(element, Pos.CENTER);
            this.enemy_dualInfo.add(Npane,i+1,1);
            i++;
        }
        //  Load Skill
        i = 0;
        //  Engimon kita
        for(Skill skill : ally.getSkill()){
            // Mastery
            StackPane mastery = new StackPane();
            ImageView masterySprite = new ImageView(skill.getSprite(25.0,25.0));
            mastery.getChildren().add(masterySprite);
            mastery.setAlignment(masterySprite, Pos.CENTER);
            this.ally_skills.add(mastery,0,i);

            // Power Skill
            Label power = new Label(Integer.toString(skill.getBasePower()));
            power.setFont(new Font("Roboto",14));
            this.ally_skills.add(power,1,i);

            // Nama Skill
            Label name = new Label(skill.getName());
            name.setFont(new Font("Roboto",14));
            this.ally_skills.add(name,2,i);

            // Element dari skill
            int j = 3;
            for(Element el : skill.getListElement()){
                StackPane element = new StackPane();
                ImageView elementImg = new ImageView(Element.getSpriteEl(el,25.0,25.0));
                element.getChildren().add(elementImg);
                element.setAlignment(elementImg, Pos.CENTER);
                this.ally_skills.add(element,j,i);
                j++;
            }
            i++;
        }
        i=0;
        //  Engimon musuh
        for(Skill skill : enemy.getSkill()){
            // Mastery
            StackPane mastery = new StackPane();
            ImageView masterySprite = new ImageView(skill.getSprite(25.0,25.0));
            mastery.getChildren().add(masterySprite);
            mastery.setAlignment(masterySprite, Pos.CENTER);
            this.enemy_skills.add(mastery,0,i);

            // Power Skill
            Label power = new Label(Integer.toString(skill.getBasePower()));
            power.setFont(new Font("Roboto",14));
            this.enemy_skills.add(power,1,i);

            // Nama Skill
            Label name = new Label(skill.getName());
            name.setFont(new Font("Roboto",14));
            this.enemy_skills.add(name,2,i);

            // Draw Element
            int j = 3;
            for(Element el : skill.getListElement()){
                StackPane element = new StackPane();
                ImageView elementImg = new ImageView(Element.getSpriteEl(el,25.0,25.0));
                element.getChildren().add(elementImg);
                element.setAlignment(elementImg, Pos.CENTER);
                this.enemy_skills.add(element,j,i);
                j++;
            }
            i++;
        }
    }

    // Apabila tombol run di tekan
    // Menutup window dan set boolean isBattle menjadi false
    public void Run(){
        this.isBattle = Boolean.FALSE;
        Stage stage = (Stage) this.button_run.getScene().getWindow();
        stage.close();
    }

    // Apabila tombol battle di tekan
    // Menutup window dan set boolean isBattle menjadi true
    public void Battle(){
        this.isBattle = Boolean.TRUE;
        Stage stage = (Stage) this.button_battle.getScene().getWindow();
        stage.close();
    }

}
