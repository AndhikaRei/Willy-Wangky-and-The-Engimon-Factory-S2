package main.java.engimon;

import main.java.element.*;
import main.java.skill.*;

public class Vaporize extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Vaporize(String name, int lives) {
        super(name, lives);
        this.species = "Vaporize";
        this.element.add(Element.Fire);
        this.element.add(Element.Water);
        this.skill.add(new Skill("Indomie rebus", "Masak aer.", 20, Element.Fire, Element.Water));
        this.slogan = "Fuwa fuwari, fuwa fuwaru~";
    }
    // ctor dengan parent
    public Vaporize(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Vaporize";
        this.element.add(Element.Fire);
        this.element.add(Element.Water);
        this.skill.add(new Skill("Indomie rebus", "Masak aer.", 20, Element.Fire, Element.Water));
        this.slogan = "Fuwa fuwari, fuwa fuwaru~";
    }
}
