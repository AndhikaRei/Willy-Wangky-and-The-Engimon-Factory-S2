package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;
import javafx.scene.image.Image;
import javafx.scene.effect.DropShadow;

public class Cryo extends Engimon implements java.io.Serializable{
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Cryo(String name, boolean isWild) {
        super(name, isWild);
        this.species = "Cryo";
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Freeze!", "I'll never use my dad's power.", 20, Element.Ice));
        this.slogan = "Motto motto~";
    }
    // ctor dengan parent
    public Cryo(String name, boolean isWild, Parent parent ) {
        super(name, isWild, parent);
        this.species = "Cryo";
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Freeze!", "I'll never use my dad's power.", 20, Element.Ice));
        this.slogan = "Motto motto~";
    }
    // ctor tanpa parent
    public Cryo(String name, int lives) {
        super(name, lives);
        this.species = "Cryo";
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Freeze!", "I'll never use my dad's power.", 20, Element.Ice));
        this.slogan = "Motto motto~";
    }
    // ctor dengan parent
    public Cryo(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Cryo";
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Freeze!", "I'll never use my dad's power.", 20, Element.Ice));
        this.slogan = "Motto motto~";
    }

    @Override
    public String getSprite(double rw, double rh){
        if (this.getLevel() < 7){
            return "./main/resources/Cryo1.png";
        } else {
            return "./main/resources/Cryo2.png";
        }
    }

    @Override
    public String getAura() {
        return "AQUAMARINE";
    }

    @Override
    public String getSlogan() {
        return this.slogan;
    }
}