package main.java.engimon.species;

import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class Electro extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Electro(String name, boolean isWild) {
        super(name, isWild);
        this.species = "Electro";
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Thunderstorm", "Lightning goes brrr.", 20, Element.Electric));
        this.slogan = "Mou sonnan ja hora~";
    }
    // ctor dengan parent
    public Electro(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "Electro";
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Thunderstorm", "Lightning goes brrr.", 20, Element.Electric));
        this.slogan = "Mou sonnan ja hora~";
    }
    // ctor tanpa parent
    public Electro(String name, int lives) {
        super(name, lives);
        this.species = "Electro";
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Thunderstorm", "Lightning goes brrr.", 20, Element.Electric));
        this.slogan = "Mou sonnan ja hora~";
    }
    // ctor dengan parent
    public Electro(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Electro";
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Thunderstorm", "Lightning goes brrr.", 20, Element.Electric));
        this.slogan = "Mou sonnan ja hora~";
    }
}