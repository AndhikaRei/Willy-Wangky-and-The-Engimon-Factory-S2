package main.java.engimon;

import main.java.element.*;
import main.java.skill.*;

public class Superconductor extends Engimon {
    /* CONSTRUCTORS */
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
}
