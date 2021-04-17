package main.java.engimon.species;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.engimon.*;
import main.java.element.*;
import main.java.skill.*;

public class Pyro extends Engimon {
    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Pyro(String name, boolean isWild) {
        super(name, isWild);
        this.species = "Pyro";
        this.element.add(Element.Fire);
        this.skill.add(new Skill("Explosion!", "Megumin approved.", 20, Element.Fire));
        this.slogan = "Se no!~";
    }
    // ctor dengan parent
    public Pyro(String name, boolean isWild, Parent parent) {
        super(name, isWild, parent);
        this.species = "Pyro";
        this.element.add(Element.Fire);
        this.skill.add(new Skill("Explosion!", "Megumin approved.", 20, Element.Fire));
        this.slogan = "Se no!~";
    }
    // ctor tanpa parent
    public Pyro(String name, int lives) {
        super(name, lives);
        this.species = "Pyro";
        this.element.add(Element.Fire);
        this.skill.add(new Skill("Explosion!", "Megumin approved.", 20, Element.Fire));
        this.slogan = "Se no!~";
    }
    // ctor dengan parent
    public Pyro(String name, int lives, Parent parent) {
        super(name, lives, parent);
        this.species = "Pyro";
        this.element.add(Element.Fire);
        this.skill.add(new Skill("Explosion!", "Megumin approved.", 20, Element.Fire));
        this.slogan = "Se no!~";
    }
    @Override
    public Image getSprite(){
        if (this.getLevel() < 7){
            return new Image("./main/resources/Pyro1.png",35,35,false,false);
        } else {
            return new Image("./main/resources/Pyro2.png",35,35,false,false);
        }
    }

    @Override
    public DropShadow getAura() {
        return new DropShadow(35, Color.RED);
    }
}
