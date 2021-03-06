package main.java.skill;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.scene.image.Image;
import main.java.element.Element;
import main.java.interfaces.Printable;

public class Skill implements Comparable<Skill>, Cloneable, Printable {
    /*** FIELDS ***/
    private String name;                    // Nama Skill
    private String desc;                    // Deskripsi Skill
    private Integer basePower;              // Base Power
    private Integer masteryLevel;           // Mastery level, maksimal level 3
    private Set<Element> elements;      // Element yang bisa mempelajari skill

    /*** METHODS ***/
    /** Default Constructor */
    public Skill() {
        name = "";
        desc = "";
        basePower = 0;
        masteryLevel = 1;
    }

    /** User-Defined Constructor */
    public Skill(String name, String desc, int basePower, Element el) {
        this.name = name;
        this.desc = desc;
        this.basePower = basePower;
        this.masteryLevel = 1;
        this.elements = new HashSet<>();
        this.elements.add(el);
    }

    public Skill(String name, String desc, int basePower, Element el1, Element el2) {
        this.name = name;
        this.desc = desc;
        this.basePower = basePower;
        this.masteryLevel = 1;
        this.elements = new HashSet<>();
        this.elements.add(el1);
        this.elements.add(el2);
    }

    public Skill(String name, String desc, int basePower, List<Element> listEl) {
        this.name = name;
        this.desc = desc;
        this.basePower = basePower;
        this.masteryLevel = 1;
        this.elements = new HashSet<>(listEl);
    }

    /** Copy Constructor **/
    public Skill(Skill other) {
        // this.name = other.name;
        // this.desc = other.desc;
        // this.basePower = other.basePower;
        // this.masteryLevel = other.masteryLevel;
        // this.listElements = new ArrayList<Element>(other.listElements);
        try {
            Skill newSk = other.cloneSkill();
            this.name = newSk.name;
            this.desc = newSk.desc;
            this.basePower = newSk.basePower;
            this.masteryLevel = newSk.masteryLevel;
            this.elements = new HashSet<>();
            this.elements.addAll(newSk.getListElement());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  

    /** SERVICE **/
    /** Menaikan mastery level skill sebanyak 1 */
    public void levelUp() {
        if (masteryLevel < 3) {
            masteryLevel++;
        } else {
            // Do Nothing
            // System.out.println("Skill telah berada batas mastery level");
        }
    }

    /** Mengubah mastery level skill menjadi 1 */
    public void levelReset() {
        masteryLevel = 1;
    }

    /** Menghitung total damage dari suatu skill */
    public int totalDamage() {
        return masteryLevel*basePower;
    }

    /** Mengcek apakah suatu elemen compatible dengan skill */
    public boolean isElementCompatible(Element el) {
        return elements.contains(el);
    }

    /** Mengcek apakah suatu list of elemen compatible dengan skill */
    public boolean isElementCompatible(List<Element> listEl) {
        boolean found = false;
        for (Element el : listEl) {
            if (elements.contains(el)) {
                found = true;
                break;
            }
        }
        return found;
    }

    /** Getter **/
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public Integer getBasePower() {
        return basePower;
    }
    public Integer getMasteryLevel() {
        return masteryLevel;
    }
    public List<Element> getListElement() {
        return new ArrayList<>(this.elements);
    }
    // Untuk tableview
    public List<Element> getElement(){
        return new ArrayList<>(this.elements);
    }

    /** Setter */
    public void setMasteryLevel(int level) {
        if (level > 3) {
            System.out.println("Melebihi batas Mastery Level, diset menjadi 3");
            this.masteryLevel = 3;
        } else {
            this.masteryLevel = level;
        }
    }

    /** Printer **/
    public void print() {
        System.out.println("-- " + name + " --");
        System.out.println(desc);
        System.out.println("BasePower    : " + basePower);
        System.out.println("MasteryLevel : " + masteryLevel);
        System.out.print("Elements     : ");
        showSkillElements();
    }

    public void printSimple() {
        System.out.println("Skill : " + name);
        System.out.println("BP/ML : " + basePower + "/" + masteryLevel);
        System.out.print("EL    : ");
        showSkillElements();
    }

    private void showSkillElements() {
        for (Element el : elements) {
            System.out.print("[");
            System.out.print(el);
            System.out.print("] ");
        }
        System.out.println("");
    }

    /** Cek apakah nama skill sama */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Skill)) {
            return false;
        }
        Skill sk = (Skill) o;
        // System.out.println("zzz");
        return this.name.equals(sk.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, basePower, masteryLevel, elements);
    }

    public Image getSprite(Double rw, Double rh){
        if(this.getMasteryLevel()==1){
            return new Image("./main/resources/mastery_1.png",rw,rh,false,false);
        } else if(this.getMasteryLevel()==2){
            return new Image("./main/resources/mastery_2.png",rw,rh,false,false);
        } else{
            return new Image("./main/resources/mastery_3.png",rw,rh,false,false);
        }
    }

    public Image getIcon(Double rw, Double rh){
        return new Image("./main/resources/".concat(this.name).concat(".jpg"),rw,rh,false,false);
    }


    @Override
    public int compareTo(Skill o) {
        /** Membandingkan Base Power Skill */
        return this.basePower - o.basePower;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // System.out.println("This clone skill");
        Skill newSk = (Skill) super.clone();
        newSk.elements = new HashSet<>(this.elements);
        // System.out.println(newSk);
        return newSk;
    }

    public Skill cloneSkill() throws CloneNotSupportedException {
        return (Skill) this.clone();     
    }

    public String toString() {
        return name +" "+ Integer.toString(masteryLevel) + "\n";
    }
}
