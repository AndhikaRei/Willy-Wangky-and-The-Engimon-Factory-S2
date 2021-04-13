package main.java.engimon;

import main.java.element.*;
import main.java.skill.*;

public class Melt extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Melt(String name, int lives) {
        super(name, lives);
        this.species = "Melt";
        this.element.add(Element.Fire);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Half-Cold Half-Hot", "Todoroki not approve.", 20, Element.Fire, Element.Ice));
        this.slogan = "Fuwafuwaru fuwafuwari~";
    }
    // ctor dengan parent
    public Melt(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Melt";
        this.element.add(Element.Fire);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Half-Cold Half-Hot", "Todoroki not approve.", 20, Element.Fire, Element.Ice));
        this.slogan = "Fuwafuwaru fuwafuwari~";
    }
}
