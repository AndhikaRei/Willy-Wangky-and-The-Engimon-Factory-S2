package main.java.engimon;

// import statements
import java.util.*;

import javafx.scene.image.Image;
import main.java.element.*;
import main.java.exception.*;
import main.java.interfaces.Printable;
import main.java.skill.*;
import main.java.inventory.*;
import javafx.scene.image.Image;
import javafx.scene.effect.DropShadow;
import java.util.List;
import java.util.Objects;

public abstract class Engimon implements Cloneable, Comparable<Engimon>, Printable {
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
    public Object clone() throws CloneNotSupportedException{
        Engimon engi = (Engimon)super.clone();
        engi.skill = new ArrayList<Skill>();
        for (Skill skill : this.getSkill()){
            engi.skill.add(skill.cloneSkill());
        }
        engi.element = new ArrayList<Element>();
        for(Element el : this.getElement()){
            engi.element.add(el);
        }
        engi.parent = new Parent();
        return engi;
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
    public char getEngimonSymbol(){
        if (this.species.equals("Pyro")) /*--- Fire ---*/
        {
            return this.level > 10 ? 'F' : 'f';
        }
        else if (this.species.equals("Hydro")) /*--- Water ---*/
        {
            return this.level > 10 ? 'W' : 'w';
        }
        else if (this.species.equals("Electro")) /*--- Electric ---*/
        {
            return this.level > 10 ? 'E' : 'e';
        }
        else if (this.species.equals("Geo")) /*--- Ground ---*/
        {
            return this.level > 10 ? 'G' : 'g';
        }
        else if (this.species.equals("Cryo")) /*--- Ice ---*/
        {
            return this.level > 10 ? 'I' : 'i'; 
        }
        else if (this.species.equals("Vaporize")) /*--- Fire/Water ---*/
        {
            return this.level > 10 ? 'A' : 'a';
        }
        else if (this.species.equals("Overload")) /*--- Fire/Electric ---*/
        {
            return this.level > 10 ? 'L' : 'l';
        }
        else if (this.species.equals("PyroCrystallize")) /*--- Fire/Ground ---*/
        {
            return this.level > 10 ? 'B' : 'b';
        }
        else if (this.species.equals("Melt")) /*--- Fire/Ice ---*/
        {
            return this.level > 10 ? 'C' : 'c';
        }
        else if (this.species.equals("ElectroCharged")) /*--- Water/Electric ---*/
        {
            return this.level > 10 ? 'D' : 'd';
        }
        else if (this.species.equals("HydroCrystallize")) /*--- Water/Ground ---*/
        {
            return this.level > 10 ? 'N' : 'n';
        }
        else if (this.species.equals("Frozen")) /*--- Water/Ice ---*/
        {
            return this.level > 10 ? 'S' : 's';
        }
        else if (this.species.equals("ElectroCrystallize")) /*--- Electric/Ground ---*/
        {
            return this.level > 10 ? 'H' : 'h';
        }
        else if (this.species.equals("Superconductor")) /*--- Electric/Ice ---*/
        {
            return this.level > 10 ? 'J' : 'j';
        }
        else
        {
            //this.species=="CryoCrystallize" /*--- Ground/Ice ---*/
            return this.level > 10 ? 'K' : 'k';
        }
    };
    // getter slogan tidak perlu

    /* SETTER */
    public void setName(String name) { this.name = name; }
    public void setLives(int lives) throws IllegalArgumentException { 
        if( lives < 0 ){
            throw new IllegalArgumentException("Illegal lives value; lives value must not be negative");
        }
        this.lives = lives; 
    }
    public void setLevel(int level) { 
        if( level < 0 ){
            throw new IllegalArgumentException("Illegal level value; level value must not be negative");
        }
        this.level = level; 
    }
    public void setExp(int exp) { 
        if( exp < 0 ){
            throw new IllegalArgumentException("Illegal exp value; exp value must not be negative");
        }
        this.exp = exp; 
    }
    public void setCumulExp(int cumul_exp) { 
        if( cumul_exp < 0 ){
            throw new IllegalArgumentException("Illegal cumul_exp value; lives vcumul_exp  must not be negative");
        }
        this.cumul_exp = cumul_exp; 
    }
    public void setParent(Parent parent) { this.parent = parent; }
    public void setLivesIsWild( boolean isWild ) { this.lives = isWild ? DEFAULT_WILD_LIVES : DEFAULT_LIVES; }
    public void decrementLive() { this.lives--; }
    // setter sisanya terlihat tidak lazim digunakan

    /* METHODS */
    // level up
    public void levelUp() {
        this.level += this.exp / 100;
        this.exp %= 100;
    }
    // level up
    public void addExp(int exp) {
        this.cumul_exp += exp;
        this.exp += exp;
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

    // add skill asumsikan semua valid
    public void addSkill(Skill sk) throws SkillAlreadyLearnedException, SkillFullException {
        // skill sudah ada
        if (this.skill.contains(sk)) {
            throw new SkillAlreadyLearnedException();
        } else {
            if (this.skill.size() == 4) {
                throw new SkillFullException();
            }
            try {
                this.skill.add(sk.cloneSkill());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        // susun dari mastery level tertinggi
        this.skill.sort((s1,s2) -> s2.getMasteryLevel().compareTo(s1.getMasteryLevel()));
    }
    
    /**
     * Add skill from skill item
     * @param skit Skill item yang akan ditambahkan
     * @throws SkillAlreadyLearnedException
     * @throws SkillFullException
     * @throws ItemNotEnoughAmountException
     * @throws SkillElementNotCompatibleException
     * @throws Exception
     */
    public void addSkill(Skill_Item skit) throws Exception {
        Skill temp = skit.getSkill();
        try {
            // skill sudah ada
            if (this.skill.contains(temp)) {
                throw new SkillAlreadyLearnedException();
            } else {
                if (this.skill.size() == 4) {
                    throw new SkillFullException();
                }
                // Pelajari skill baru
                temp = skit.learn(this.element);
                this.skill.add(new Skill(temp));
                
            }
        } catch (Exception e) {
            throw e;
        }
        
    }
    public void replaceSkill(int index, Skill_Item skit) throws Exception {
        Skill temp = skit.getSkill();
        try {
            // skill sudah ada
            if (this.skill.contains(temp)) {
                throw new SkillAlreadyLearnedException();
            } else {
                // indeks tidak sesuai skill yang ada
                if (this.skill.size() <= index) {
                    throw new IndexOutOfBoundsException();
                }
                temp = skit.learn(this.element);
                this.skill.set(index, temp);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // print engimon di cli
    public void print() {
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

    public void printSimple() {
        System.out.printf("%s - %s - %d lives\n", this.name, this.species, this.lives);
        System.out.printf("Level : %d \nCurrent Exp : %d \nTotal Exp : %d\n", this.level, this.exp, this.cumul_exp);
        System.out.printf("[%s]", this.element.get(0).name());
        if (this.isOneElement()) {
            System.out.println();
        }
        else {
            System.out.printf("[%s]\n",this.element.get(1).name());
        }
    }

    public abstract Image getSprite(double rw, double rh);
    public abstract DropShadow getAura();
    public abstract String getSlogan();

    @Override
    public int compareTo(Engimon E) {
        if(Element.getId(this.getElement().get(0)) - Element.getId(E.getElement().get(0)) == 0){
            return this.getLevel() - E.getLevel();
        }else{
            return Element.getId(this.getElement().get(0)) - Element.getId(E.getElement().get(0));
        }

//        if(this.getElement() == E.getElement()){
//            return this.getCumulExp() - E.getCumulExp();
//        }else{
//            if(Element.advantage(this.getElement(), E.getElement()) > 1){
//                return 1;
//            }
//            return -1;
//        }

    }
}


