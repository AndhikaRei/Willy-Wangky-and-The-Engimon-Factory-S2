package sample.DetailEngimon;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import main.java.element.Element;
import main.java.engimon.Engimon;
import main.java.skill.Skill;


public class DetailEngimonController {
    // Placeholder untuk meletakkan foto engimon kita
    @FXML private ImageView ally_sprite;

    // Info dasar (yang bisa ditulis satu kolom) dari engimon kita dan musuh
    // Info nama spesies, nama engimon, dan level engimon
    @FXML private Label ally_speciesName;
    @FXML private Label ally_name;
    @FXML private Label ally_level;
    @FXML private Label ally_slogan;
    @FXML private Label ally_parent;


    // Info yang perlu ditulis dalam beberapa kolom engimon kita dan musuh
    // Info dari element dan darah engimon
    @FXML private GridPane ally_dualInfo;
    // Info dari skill
    @FXML private GridPane ally_skills;

    // Melakukan load data sesuai parameter engimon yang disuplai
    public void loadData(Engimon ally){

        //  Load image sesuai engimon
        this.ally_sprite.setImage(new Image(ally.getSprite(150,150),150,150,false,false));

        //  Load info dasar berupa nama spesies, nama engimon, dan level engimon
        this.ally_speciesName.setText(ally.getSpecies());
        this.ally_name.setText(ally.getName());
        this.ally_level.setText(Integer.toString(ally.getLevel()));
        this.ally_slogan.setText(ally.getSlogan());
        this.ally_parent.setText(ally.getParent().stringParent());

        //  Load info panjang
        //  Info live
        Image heart = new Image("main/resources/heart.png",20,20,false,false);
        for (int i =0; i < ally.getLives(); i++){
            StackPane Npane = new StackPane();
            ImageView heartView = new ImageView(heart);
            Npane.getChildren().add(heartView);
            Npane.setAlignment(heartView, Pos.CENTER);
            this.ally_dualInfo.add(Npane,i+1,0);
        }
        //  Load element
        int i = 0;
        for(Element el : ally.getElement()){
            StackPane Npane = new StackPane();
            ImageView element = new ImageView(Element.getSpriteEl(el,25.0,25.0));
            Npane.getChildren().add(element);
            Npane.setAlignment(element, Pos.CENTER);
            this.ally_dualInfo.add(Npane,i+1,1);
            i++;
        }
        //  Load Skill
        i = 0;

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

            // Deskripsi Skill
            Label description = new Label(skill.getDesc());
            description.setFont(new Font("Roboto",14));
            this.ally_skills.add(description,3,i);

            // Element dari skill
            int j = 4;
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
    }

}
