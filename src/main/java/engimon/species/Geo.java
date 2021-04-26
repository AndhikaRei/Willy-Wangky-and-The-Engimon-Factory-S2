package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class Geo extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Geo(String name, boolean isWild) {
        super(name, isWild);
        this.species = "Geo";
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Tanah tinggi!", "Ez cover.", 20, Element.Ground));
        this.slogan = "Kokoro wa shinka suru yo~";
    }
    // ctor dengan parent
    public Geo(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "Geo";
        this.element.add(Element.Ground);
        this.skill.add(new Skill("Tanah tinggi!", "Ez cover.", 20, Element.Ground));
        this.slogan = "Kokoro wa shinka suru yo~";
    }
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
    @Override
    public Image getSprite(double rw, double rh){
        if (this.getLevel() < 50){
            return new Image("./main/resources/Geo1.png",rw,rh,false,false);
        } else {
            return new Image("./main/resources/Geo2.png",rw,rh,false,false);
        }
    }

    @Override
    public DropShadow getAura() {
        return new DropShadow(35, Color.SADDLEBROWN);
    }

    @Override
    public String getSlogan() {
        return this.slogan;
    }
}