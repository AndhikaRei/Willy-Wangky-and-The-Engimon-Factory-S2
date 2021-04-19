package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class ElectroCharged extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public ElectroCharged(String name, boolean isWild) {
        super(name, isWild);
        this.species = "ElectroCharged";
        this.element.add(Element.Water);
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Charged Water!", "Hati-hati kesetrum.", 20, Element.Water, Element.Electric));
        this.slogan = "Anata ga waratte iru~";
    }
    // ctor dengan parent
    public ElectroCharged(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "ElectroCharged";
        this.element.add(Element.Water);
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Charged Water!", "Hati-hati kesetrum.", 20, Element.Water, Element.Electric));
        this.slogan = "Anata ga waratte iru~";
    }
    // ctor tanpa parent
    public ElectroCharged(String name, int lives) {
        super(name, lives);
        this.species = "ElectroCharged";
        this.element.add(Element.Water);
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Charged Water!", "Hati-hati kesetrum.", 20, Element.Water, Element.Electric));
        this.slogan = "Anata ga waratte iru~";
    }
    // ctor dengan parent
    public ElectroCharged(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "ElectroCharged";
        this.element.add(Element.Water);
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Charged Water!", "Hati-hati kesetrum.", 20, Element.Water, Element.Electric));
        this.slogan = "Anata ga waratte iru~";
    }
    @Override
    public Image getSprite(double rw, double rh){
        if (this.getLevel() < 7){
            return new Image("./main/resources/ElectroCharged1.png",rw,rh,false,false);
        } else {
            return new Image("./main/resources/ElectroCharged2.png",rw,rh,false,false);
        }
    }

    @Override
    public DropShadow getAura() {
        return new DropShadow(35, Color.GOLD);
    }
    @Override
    public String getSlogan() {
        return this.slogan;
    }
}
