package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class Hydro extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Hydro(String name, boolean isWild) {
        super(name, isWild);
        this.species = "Hydro";
        this.element.add(Element.Water);
        this.skill.add(new Skill("Purification!", "Aqua approved.", 20, Element.Water));
        this.slogan = "Demo sonnan ja dame~";
    }
    // ctor dengan parent
    public Hydro(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "Hydro";
        this.element.add(Element.Water);
        this.skill.add(new Skill("Purification!", "Aqua approved.", 20, Element.Water));
        this.slogan = "Demo sonnan ja dame~";
    }
    // ctor tanpa parent
    public Hydro(String name, int lives) {
        super(name, lives);
        this.species = "Hydro";
        this.element.add(Element.Water);
        this.skill.add(new Skill("Purification!", "Aqua approved.", 20, Element.Water));
        this.slogan = "Demo sonnan ja dame~";
    }
    // ctor dengan parent
    public Hydro(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Hydro";
        this.element.add(Element.Water);
        this.skill.add(new Skill("Purification!", "Aqua approved.", 20, Element.Water));
        this.slogan = "Demo sonnan ja dame~";
    }
    @Override
    public String getSprite(double rw, double rh){
        if (this.getLevel() < 7){
            return "./main/resources/Hydro1.png";
        } else {
            return "./main/resources/Hydro2.png";
        }
    }

    @Override
    public String getAura() {
        return "ROYALBLUE";
    }

    @Override
    public String getSlogan() {
        return this.slogan;
    }
}
