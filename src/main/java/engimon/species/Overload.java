package main.java.engimon.species;

import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class Overload extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Overload(String name, boolean isWild) {
        super(name, isWild);
        this.species = "Overload";
        this.element.add(Element.Fire);
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Korsleting", "Api berpadu dengan Listrik", 20, Element.Fire, Element.Electric));
        this.slogan = "Anata ga namae wo yobu~";
    }
    // ctor dengan parent
    public Overload(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "Overload";
        this.element.add(Element.Fire);
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Korsleting", "Api berpadu dengan Listrik", 20, Element.Fire, Element.Electric));
        this.slogan = "Anata ga namae wo yobu~";
    }
    // ctor tanpa parent
    public Overload(String name, int lives) {
        super(name, lives);
        this.species = "Overload";
        this.element.add(Element.Fire);
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Korsleting", "Api berpadu dengan Listrik", 20, Element.Fire, Element.Electric));
        this.slogan = "Anata ga namae wo yobu~";
    }
    // ctor dengan parent
    public Overload(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Overload";
        this.element.add(Element.Fire);
        this.element.add(Element.Electric);
        this.skill.add(new Skill("Korsleting", "Api berpadu dengan Listrik", 20, Element.Fire, Element.Electric));
        this.slogan = "Anata ga namae wo yobu~";
    }
}
