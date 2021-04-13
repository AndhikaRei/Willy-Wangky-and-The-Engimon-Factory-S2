package main.java.engimon;

// import statements
import java.util.*;
import main.java.element.*;
import main.java.skill.*;

public abstract class Engimon {
    /* FIELDS */
    protected String name;
    protected int lives;
    protected String species;
    protected Parent parent;
    protected List<Element> element = new ArrayList<>();
    protected List<Skill> skill = new ArrayList<>();
    protected int level;
    protected int exp;
    protected int cumul_exp;
    protected String slogan;

    /* CONSTRUCTORS */
    // ctor tanpa parent, lives 3 untuk player engimon, 1 untuk wild engimon
    public Engimon(String name, int lives) {
        this.name = name;
        this.lives = lives;
        this.parent = new Parent();
        this.level = 0;
        this.exp = 0;
        this.cumul_exp = 0;
    }
    // ctor dengan parent, lives 3 untuk player engimon, 1 untuk wild engimon
    public Engimon(String name, int lives, Parent parent) {
        this.name = name;
        this.lives = lives;
        this.parent = new Parent(parent);
        this.level = 0;
        this.exp = 0;
        this.cumul_exp = 0;
    }
    // cctor lengkap
    public Engimon(Engimon engimon) {
        this.name = engimon.name;
        this.lives = engimon.lives;
        this.species = engimon.species;
        this.parent = new Parent(engimon.parent);
        this.element.add(engimon.element.get(0));
        if (!engimon.isOneElement()) {
            this.element.add(engimon.element.get(1));
        }
        for (int i = 0; i < engimon.skill.size(); i++) {
            this.skill.add(new Skill(engimon.skill.get(i)));
        }
        this.level = engimon.level;
        this.exp = engimon.exp;
        this.cumul_exp = engimon.cumul_exp;
        this.slogan = engimon.slogan;
    }

    /* GETTER */
    public String getName() { return this.name; }
    public int getLives() { return this.lives; }
    public String getSpecies() { return this.species; }
    public Parent getParent() { return this.parent; }
    public List<Element> getElement() { return this.element; }
    public List<Skill> getSkill() { return this.skill; }
    public int getLevel() { return this.level; }
    public int getExp() { return this.exp; }
    public int getCumulExp() { return this.cumul_exp; }
    // getter slogan tidak perlu

    /* SETTER */
    public void setName(String name) { this.name = name; }
    public void setLives(int lives) { this.lives = lives; }
    public void setLevel(int level) { this.level = level; }
    public void setExp(int exp) { this.exp = exp; }
    public void setCumulExp(int cumul_exp) { this.cumul_exp = cumul_exp; }
    // setter sisanya terlihat tidak lazim digunakan

    /* METHODS */
    // level up
    public void levelUp() {
        this.level += this.exp / 100;
        this.exp %= 100;
    }
    // cek jika engimon 1 elemen
    public boolean isOneElement() { return this.element.size() == 1; }
    // interact
    public void interact() { System.out.printf("%s : %s\n",this.name, this.slogan); }
    // print engimon di cli
    public void printEngimon() {
        System.out.printf("%s - %s - %d lives\n", this.name, this.species, this.lives);
        System.out.printf("%d, %d, %d\n", this.level, this.exp, this.cumul_exp);
        System.out.printf("[%s]", this.element.get(0).name());
        if (this.isOneElement()) { System.out.println(); }
        else { System.out.printf("[%s]\n",this.element.get(1).name()); }
        this.parent.printParent();
        this.interact();
    }
}



