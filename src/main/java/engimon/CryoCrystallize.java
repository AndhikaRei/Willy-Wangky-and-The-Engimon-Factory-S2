package main.java.engimon;

import main.java.element.*;
import main.java.skill.*;

public class CryoCrystallize extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public CryoCrystallize(String name, int lives) {
        super(name, lives);
        this.species = "CryoCrystallize";
        this.element.add(Element.Ground);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Golem Es!", "Ez tank 4.", 20, Element.Ground, Element.Ice));
        this.slogan = "Shiawase na no~";
    }
    // ctor dengan parent
    public CryoCrystallize(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "CryoCrystallize";
        this.element.add(Element.Ground);
        this.element.add(Element.Ice);
        this.skill.add(new Skill("Golem Es!", "Ez tank 4.", 20, Element.Ground, Element.Ice));
        this.slogan = "Shiawase na no~";
    }
}
