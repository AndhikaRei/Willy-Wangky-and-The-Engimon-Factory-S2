package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class HydroCrystallize extends Engimon implements java.io.Serializable{
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public HydroCrystallize(String name, boolean isWild) {
        super(name, isWild);
        this.species = "HydroCrystallize";
        this.element.add(Element.Water);
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Golem Air!", "Ez tank 2.", 20, Element.Water, Element.Ground));
        this.slogan = "Sore dake de egao ni naru~";
    }
    // ctor dengan parent
    public HydroCrystallize(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "HydroCrystallize";
        this.element.add(Element.Water);
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Golem Air!", "Ez tank 2.", 20, Element.Water, Element.Ground));
        this.slogan = "Sore dake de egao ni naru~";
    }
    // ctor tanpa parent
    public HydroCrystallize(String name, int lives) {
        super(name, lives);
        this.species = "HydroCrystallize";
        this.element.add(Element.Water);
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Golem Air!", "Ez tank 2.", 20, Element.Water, Element.Ground));
        this.slogan = "Sore dake de egao ni naru~";
    }
    // ctor dengan parent
    public HydroCrystallize(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "HydroCrystallize";
        this.element.add(Element.Water);
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Golem Air!", "Ez tank 2.", 20, Element.Water, Element.Ground));
        this.slogan = "Sore dake de egao ni naru~";
    }
    @Override
    public String getSprite(double rw, double rh){
        if (this.getLevel() < 7){
            return "./main/resources/HydroCrystallize1.png";
        } else {
            return "./main/resources/HydroCrystallize2.png";
        }
    }

    @Override
    public String getAura() {
        return "SLATEBLUE";
    }

    @Override
    public String getSlogan() {
        return this.slogan;
    }
}
