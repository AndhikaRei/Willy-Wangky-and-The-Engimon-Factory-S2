package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class Frozen extends Engimon {
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
    public Image getSprite(double rw, double rh){
        if (this.getLevel() < 7){
            return new Image("./main/resources/Frozen1.png",rw,rh,false,false);
        } else {
            return new Image("./main/resources/Frozen2.png",rw,rh,false,false);
        }
    }

    @Override
    public DropShadow getAura() {
        return new DropShadow(35, Color.DARKTURQUOISE);
    }
}
