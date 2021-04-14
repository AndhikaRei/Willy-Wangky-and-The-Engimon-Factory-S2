package main.java.engimon;

import main.java.element.*;
import main.java.skill.*;

public class HydroCrystallize extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public HydroCrystallize(String name, int lives) {
        super(name, lives);
        this.species = "HydroCrystallize";
        this.element.add(Element.Water);
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Golem Air!", "Ez tank 2.", 20, Element.Water, Element.Ground));
        this.slogan = "Sore dake de egao ni naru~";
    }
    // ctor dengan parent
    public HydroCrystallize(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "HydroCrystallize";
        this.element.add(Element.Water);
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Golem Air!", "Ez tank 2.", 20, Element.Water, Element.Ground));
        this.slogan = "Sore dake de egao ni naru~";
    }
}
