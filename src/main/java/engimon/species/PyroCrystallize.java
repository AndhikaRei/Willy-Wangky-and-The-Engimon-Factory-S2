package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class PyroCrystallize extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public PyroCrystallize(String name, boolean isWild) {
        super(name, isWild);
        this.species = "PyroCrystallize";
        this.element.add(Element.Fire);
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Golem Api!", "Ez tank.", 20, Element.Fire, Element.Ground));
        this.slogan = "Sore dake de chuu e ukabu~";
    }
    // ctor dengan parent
    public PyroCrystallize(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "PyroCrystallize";
        this.element.add(Element.Fire);
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Golem Api!", "Ez tank.", 20, Element.Fire, Element.Ground));
        this.slogan = "Sore dake de chuu e ukabu~";
    }
    // ctor tanpa parent
    public PyroCrystallize(String name, int lives) {
        super(name, lives);
        this.species = "PyroCrystallize";
        this.element.add(Element.Fire);
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Golem Api!", "Ez tank.", 20, Element.Fire, Element.Ground));
        this.slogan = "Sore dake de chuu e ukabu~";
    }
    // ctor dengan parent
    public PyroCrystallize(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "PyroCrystallize";
        this.element.add(Element.Fire);
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Golem Api!", "Ez tank.", 20, Element.Fire, Element.Ground));
        this.slogan = "Sore dake de chuu e ukabu~";
    }
    @Override
    public Image getSprite(double rw, double rh){
        if (this.getLevel() < 50){
            return new Image("./main/resources/PyroCrystallize1.png",rw,rh,false,false);
        } else {
            return new Image("./main/resources/PyroCrystallize2.png",rw,rh,false,false);
        }
    }

    @Override
    public DropShadow getAura() {
        return new DropShadow(35, Color.FIREBRICK);
    }

    @Override
    public String getSlogan() {
        return this.slogan;
    }
}