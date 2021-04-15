package main.java.engimon;

// import statements
import java.util.*;
import main.java.element.*;
import main.java.skill.*;

public abstract class Engimon implements Cloneable {
    /* FINAL ATTRIBUTES */
    private final int DEFAULT_LIVES = 3;
    private final int DEFAULT_WILD_LIVES = 1;

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
    // Using Wild Flag
    public Engimon ( String name, boolean isWild ){
        this.name = name;
        this.lives = isWild ? DEFAULT_WILD_LIVES : DEFAULT_LIVES;
        this.parent = new Parent();
        this.level = 0;
        this.exp = 0;
        this.cumul_exp = 0;
    }

    public Engimon(String name, boolean isWild, Parent parent ){
        this.name = name;
        this.lives = isWild ? DEFAULT_WILD_LIVES : DEFAULT_LIVES;
        this.parent = new Parent();
        this.level = 0;
        this.exp = 0;
        this.cumul_exp = 0;
    }

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

    public Engimon cloneEngimon() throws CloneNotSupportedException {
        return (Engimon) this.clone();
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
    public void setParent(Parent parent) { this.parent = parent; }
    public void setLivesIsWild( boolean isWild ) { this.lives = isWild ? DEFAULT_WILD_LIVES : DEFAULT_LIVES; }
    // setter sisanya terlihat tidak lazim digunakan

    /* METHODS */
    // level up
    public void levelUp() {
        this.level += this.exp / 100;
        this.exp %= 100;
    }
    // cek jika engimon 1 elemen
    public boolean isOneElement() { 
        return this.element.size() == 1; 
    }

    // interact
    public String interact() { 
        return "%s : %s\n" + this.name + this.slogan; 
    }

    // addSkill
    public void addSkill(Skill sk) {
        this.skill.add(sk);

        // Susun dari mastery level tertinggi
        this.skill.sort((s1,s2) -> s2.getMasteryLevel().compareTo(s1.getMasteryLevel()));
    }

    // print engimon di cli
    public void printEngimon() {
        System.out.printf("%s - %s - %d lives\n", this.name, this.species, this.lives);
        System.out.printf("Level : %d \nCurrent Exp : %d \nTotal Exp : %d\n", this.level, this.exp, this.cumul_exp);
        System.out.printf("[%s]", this.element.get(0).name());
        if (this.isOneElement()) { 
            System.out.println(); 
        }
        else { 
            System.out.printf("[%s]\n",this.element.get(1).name()); 
        }

        if(!this.parent.isEmpty()){
            this.parent.printParent();
        }
        this.interact();
    }
}


