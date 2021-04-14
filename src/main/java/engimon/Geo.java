package main.java.engimon;

import main.java.element.*;
import main.java.skill.*;

public class Geo extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Geo(String name, int lives) {
        super(name, lives);
        this.species = "Geo";
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Tanah tinggi!", "Ez cover.", 20, Element.Ground));
        this.slogan = "Kokoro wa shinka suru yo~";
    }
    // ctor dengan parent
    public Geo(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Geo";
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Tanah tinggi!", "Ez cover.", 20, Element.Ground));
        this.slogan = "Kokoro wa shinka suru yo~";
    }
}