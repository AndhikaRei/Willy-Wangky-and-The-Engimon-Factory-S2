package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class Melt extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Melt(String name, boolean isWild) {
        super(name, isWild);
        this.species = "Melt";
        this.element.add(Element.Fire);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Half-Cold Half-Hot", "Todoroki not approve.", 20, Element.Fire, Element.Ice));
        this.slogan = "Fuwafuwaru fuwafuwari~";
    }
    // ctor dengan parent
    public Melt(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "Melt";
        this.element.add(Element.Fire);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Half-Cold Half-Hot", "Todoroki not approve.", 20, Element.Fire, Element.Ice));
        this.slogan = "Fuwafuwaru fuwafuwari~";
    }
    // ctor tanpa parent
    public Melt(String name, int lives) {
        super(name, lives);
        this.species = "Melt";
        this.element.add(Element.Fire);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Half-Cold Half-Hot", "Todoroki not approve.", 20, Element.Fire, Element.Ice));
        this.slogan = "Fuwafuwaru fuwafuwari~";
    }
    // ctor dengan parent
    public Melt(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Melt";
        this.element.add(Element.Fire);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Half-Cold Half-Hot", "Todoroki not approve.", 20, Element.Fire, Element.Ice));
        this.slogan = "Fuwafuwaru fuwafuwari~";
    }
    @Override
    public Image getSprite(double rw, double rh){
        if (this.getLevel() < 50){
            return new Image("./main/resources/Melt1.png",rw,rh,false,false);
        } else {
            return new Image("./main/resources/Melt2.png",rw,rh,false,false);
        }
    }

    @Override
    public DropShadow getAura() {
        return new DropShadow(35, Color.DARKRED);
    }

    @Override
    public String getSlogan() {
        return this.slogan;
    }
}
