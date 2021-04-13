package main.java.engimon;

import main.java.element.*;
import main.java.skill.*;

public class Hydro extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Hydro(String name, int lives) {
        super(name, lives);
        this.species = "Hydro";
        this.element.add(Element.Water);
        this.skill.add(new Skill("Purification!", "Aqua approved.", 20, Element.Water));
        this.slogan = "Demo sonnan ja dame~";
    }
    // ctor dengan parent
    public Hydro(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Hydro";
        this.element.add(Element.Water);
        this.skill.add(new Skill("Purification!", "Aqua approved.", 20, Element.Water));
        this.slogan = "Demo sonnan ja dame~";
    }
}
