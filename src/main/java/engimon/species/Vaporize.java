package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class Vaporize extends Engimon implements java.io.Serializable{
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Vaporize(String name, boolean isWild) {
        super(name, isWild);
        this.species = "Vaporize";
        this.element.add(Element.Fire);
        this.element.add(Element.Water);
        this.skill.add(new Skill("Indomie rebus", "Masak aer.", 20, Element.Fire, Element.Water));
        this.slogan = "Fuwa fuwari, fuwa fuwaru~";
    }
    // ctor dengan parent
    public Vaporize(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "Vaporize";
        this.element.add(Element.Fire);
        this.element.add(Element.Water);
        this.skill.add(new Skill("Indomie rebus", "Masak aer.", 20, Element.Fire, Element.Water));
        this.slogan = "Fuwa fuwari, fuwa fuwaru~";
    }
    // ctor tanpa parent
    public Vaporize(String name, int lives) {
        super(name, lives);
        this.species = "Vaporize";
        this.element.add(Element.Fire);
        this.element.add(Element.Water);
        this.skill.add(new Skill("Indomie rebus", "Masak aer.", 20, Element.Fire, Element.Water));
        this.slogan = "Fuwa fuwari, fuwa fuwaru~";
    }
    // ctor dengan parent
    public Vaporize(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Vaporize";
        this.element.add(Element.Fire);
        this.element.add(Element.Water);
        this.skill.add(new Skill("Indomie rebus", "Masak aer.", 20, Element.Fire, Element.Water));
        this.slogan = "Fuwa fuwari, fuwa fuwaru~";
    }
    @Override
    public String getSprite(double rw, double rh){
        if (this.getLevel() < 7){
            return "./main/resources/Vaporize1.png";
        } else {
            return "./main/resources/Vaporize2.png";
        }
    }

    @Override
    public String getAura() {
        return "LIGHTGRAY";
    }
    @Override
    public String getSlogan() {
        return this.slogan;
    }
}
