package main.java.engimon;

import main.java.element.*;
import main.java.skill.*;

public class Cryo extends Engimon {
    /* CONSTRUCTORS */
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
}