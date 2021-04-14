package main.java.engimon;

import main.java.element.*;
import main.java.skill.*;

public class Pyro extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Pyro(String name, int lives) {
        super(name, lives);
        this.species = "Pyro";
        this.element.add(Element.Fire);
        this.skill.add(new Skill("Explosion!", "Megumin approved.", 20, Element.Fire));
        this.slogan = "Se no!~";
    }
    // ctor dengan parent
    public Pyro(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Pyro";
        this.element.add(Element.Fire);
        this.skill.add(new Skill("Explosion!", "Megumin approved.", 20, Element.Fire));
        this.slogan = "Se no!~";
    }
}
