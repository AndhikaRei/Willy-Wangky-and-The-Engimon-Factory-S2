package main.java.engimon.species;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;
import javafx.scene.image.Image;
import javafx.scene.effect.DropShadow;

public class CryoCrystallize extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public CryoCrystallize(String name, boolean isWild) {
        super(name, isWild);
        this.species = "CryoCrystallize";
        this.element.add(Element.Ground);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Golem Es!", "Ez tank 4.", 20, Element.Ground, Element.Ice));
        this.slogan = "Shiawase na no~";
    }
    // ctor dengan parent
    public CryoCrystallize(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "CryoCrystallize";
        this.element.add(Element.Ground);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Golem Es!", "Ez tank 4.", 20, Element.Ground, Element.Ice));
        this.slogan = "Shiawase na no~";
    }
    // ctor tanpa parent
    public CryoCrystallize(String name, int lives) {
        super(name, lives);
        this.species = "CryoCrystallize";
        this.element.add(Element.Ground);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Golem Es!", "Ez tank 4.", 20, Element.Ground, Element.Ice));
        this.slogan = "Shiawase na no~";
    }
    // ctor dengan parent
    public CryoCrystallize(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "CryoCrystallize";
        this.element.add(Element.Ground);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Golem Es!", "Ez tank 4.", 20, Element.Ground, Element.Ice));
        this.slogan = "Shiawase na no~";
    }
    @Override
    public String getSprite(double rw, double rh){
        if (this.getLevel() < 7){
            return "./main/resources/CryoCrystallize1.png";
        } else {
            return "./main/resources/CryoCrystallize2.png";
        }
    }

    @Override
    public String getSlogan() {
        return this.slogan;
    }

    @Override
    public String getAura() {
        return "GREEN";
    }
}
