package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class Superconductor extends Engimon implements java.io.Serializable{
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Superconductor(String name, boolean isWild) {
        super(name, isWild);
        this.species = "Superconductor";
        this.element.add(Element.Electric);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Atta Halilintar", "Masa listrik bisa berpadu dengan es?", 20, Element.Electric, Element.Ice));
        this.slogan = "Meguriaeta koto ga~";
    }
    // ctor dengan parent
    public Superconductor(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "Superconductor";
        this.element.add(Element.Electric);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Atta Halilintar", "Masa listrik bisa berpadu dengan es?", 20, Element.Electric, Element.Ice));
        this.slogan = "Meguriaeta koto ga~";
    }
    // ctor tanpa parent
    public Superconductor(String name, int lives) {
        super(name, lives);
        this.species = "Superconductor";
        this.element.add(Element.Electric);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Atta Halilintar", "Masa listrik bisa berpadu dengan es?", 20, Element.Electric, Element.Ice));
        this.slogan = "Meguriaeta koto ga~";
    }
    // ctor dengan parent
    public Superconductor(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Superconductor";
        this.element.add(Element.Electric);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Atta Halilintar", "Masa listrik bisa berpadu dengan es?", 20, Element.Electric, Element.Ice));
        this.slogan = "Meguriaeta koto ga~";
    }
    @Override
    public String getSprite(double rw, double rh){
        if (this.getLevel() < 7){
            return "./main/resources/Superconductor1.png";
        } else {
            return "./main/resources/Superconductor2.png";
        }
    }

    @Override
    public String getAura() {
        return "ORANGERED";
    }

    @Override
    public String getSlogan() {
        return this.slogan;
    }
}
