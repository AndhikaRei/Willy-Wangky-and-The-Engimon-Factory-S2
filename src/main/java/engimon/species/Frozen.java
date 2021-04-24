package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class Frozen extends Engimon implements java.io.Serializable{
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Frozen(String name, boolean isWild) {
        super(name, isWild);
        this.species = "Frozen";
        this.element.add(Element.Water);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Let it go~", "Elsa approved.", 20, Element.Water, Element.Ice));
        this.slogan = "Kami-sama arigatou~";
    }
    // ctor dengan parent
    public Frozen(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "Frozen";
        this.element.add(Element.Water);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Let it go~", "Elsa approved.", 20, Element.Water, Element.Ice));
        this.slogan = "Kami-sama arigatou~";
    }
    // ctor tanpa parent
    public Frozen(String name, int lives) {
        super(name, lives);
        this.species = "Frozen";
        this.element.add(Element.Water);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Let it go~", "Elsa approved.", 20, Element.Water, Element.Ice));
        this.slogan = "Kami-sama arigatou~";
    }
    // ctor dengan parent
    public Frozen(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Frozen";
        this.element.add(Element.Water);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Let it go~", "Elsa approved.", 20, Element.Water, Element.Ice));
        this.slogan = "Kami-sama arigatou~";
    }
    @Override
    public String getSprite(double rw, double rh){
        if (this.getLevel() < 7){
            return "./main/resources/Frozen1.png";
        } else {
            return "./main/resources/Frozen2.png";
        }
    }

    @Override
    public String getAura() {
        return "DARKTURQUOISE";
    }

    @Override
    public String getSlogan() {
        return this.slogan;
    }
}
