package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class Superconductor extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Superconductor(String name, boolean isWild) {
        super(name, isWild);
        this.species = "Superconductor";
        this.element.add(Element.Electric);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Species-self-explanatory.", "Masa listrik bisa berpadu dengan es?", 20, Element.Electric, Element.Ice));
        this.slogan = "Meguriaeta koto ga~";
    }
    // ctor dengan parent
    public Superconductor(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "Superconductor";
        this.element.add(Element.Electric);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Species-self-explanatory.", "Masa listrik bisa berpadu dengan es?", 20, Element.Electric, Element.Ice));
        this.slogan = "Meguriaeta koto ga~";
    }
    // ctor tanpa parent
    public Superconductor(String name, int lives) {
        super(name, lives);
        this.species = "Superconductor";
        this.element.add(Element.Electric);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Species-self-explanatory.", "Masa listrik bisa berpadu dengan es?", 20, Element.Electric, Element.Ice));
        this.slogan = "Meguriaeta koto ga~";
    }
    // ctor dengan parent
    public Superconductor(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Superconductor";
        this.element.add(Element.Electric);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Species-self-explanatory.", "Masa listrik bisa berpadu dengan es?", 20, Element.Electric, Element.Ice));
        this.slogan = "Meguriaeta koto ga~";
    }
    @Override
    public Image getSprite(){
        if (this.getLevel() < 7){
            return new Image("./main/resources/Superconductor1.png",35,35,false,false);
        } else {
            return new Image("./main/resources/Superconductor2.png",35,35,false,false);
        }
    }

    @Override
    public DropShadow getAura() {
        return new DropShadow(35, Color.ORANGERED);
    }
}
