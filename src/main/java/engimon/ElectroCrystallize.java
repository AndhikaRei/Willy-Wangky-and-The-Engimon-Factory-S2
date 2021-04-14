package main.java.engimon;

import main.java.element.*;
import main.java.skill.*;

public class ElectroCrystallize extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public ElectroCrystallize(String name, int lives) {
        super(name, lives);
        this.species = "ElectroCrystallize";
        this.element.add(Element.Electric);
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Golem Petir!", "Ez tank 3.", 20, Element.Electric, Element.Ground));
        this.slogan = "Unmei no itazura demo~";
    }
    // ctor dengan parent
    public ElectroCrystallize(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "ElectroCrystallize";
        this.element.add(Element.Electric);
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Golem Petir!", "Ez tank 3.", 20, Element.Electric, Element.Ground));
        this.slogan = "Unmei no itazura demo~";
    }
}
