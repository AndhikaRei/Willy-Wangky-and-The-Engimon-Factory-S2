package main.java.engimon;

import main.java.element.*;
import main.java.skill.*;

public class ElectroCharged extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public ElectroCharged(String name, int lives) {
        super(name, lives);
        this.species = "ElectroCharged";
        this.element.add(Element.Water);
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Charged Water!", "Hati-hati kesetrum.", 20, Element.Water, Element.Electric));
        this.slogan = "Anata ga waratte iru~";
    }
    // ctor dengan parent
    public ElectroCharged(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "ElectroCharged";
        this.element.add(Element.Water);
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Charged Water!", "Hati-hati kesetrum.", 20, Element.Water, Element.Electric));
        this.slogan = "Anata ga waratte iru~";
    }
}
