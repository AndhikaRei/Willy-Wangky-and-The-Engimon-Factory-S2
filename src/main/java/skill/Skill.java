package main.java.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.image.Image;
import main.java.element.Element;

public class Skill implements Comparable<Skill> {
    /*** FIELDS ***/
    private String name;                    // Nama Skill
    private String desc;                    // Deskripsi Skill
    private Integer basePower;              // Base Power
    private Integer masteryLevel;           // Mastery level, maksimal level 3
    private List<Element> listElements;     // Element yang bisa mempelajari skill

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
        this.listElements = new ArrayList<Element>();
        this.listElements.add(el);
    }

    public Skill(String name, String desc, int basePower, Element el1, Element el2) {
        this.name = name;
        this.desc = desc;
        this.basePower = basePower;
        this.masteryLevel = 1;
        this.listElements = new ArrayList<Element>();
        this.listElements.add(el1);
        this.listElements.add(el2);
    }

    public Skill(String name, String desc, int basePower, List<Element> listEl) {
        this.name = name;
        this.desc = desc;
        this.basePower = basePower;
        this.masteryLevel = 1;
        this.listElements = new ArrayList<Element>(listEl);
    }

    /** Copy Constructor **/
    public Skill(Skill other) {
        this.name = other.name;
        this.desc = other.desc;
        this.basePower = other.basePower;
        this.masteryLevel = other.masteryLevel;
        this.listElements = new ArrayList<Element>(other.listElements);
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
        return listElements.contains(el);
    }

    /** Mengcek apakah suatu list of elemen compatible dengan skill */
    public boolean isElementCompatible(List<Element> listEl) {
        boolean found = false;
        for (Element el : listEl) {
            if (listElements.contains(el)) {
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
        return listElements;
    }

    /** Printer **/
    public void showSkill() {
        System.out.println("-- " + name + " --");
        System.out.println(desc);
        System.out.println("BasePower    : " + basePower);
        System.out.println("MasteryLevel : " + masteryLevel);
        System.out.print("Elements     : ");
        for (int i = 0; i < listElements.size(); i++) {
            System.out.print(listElements.get(i));
            if (i < listElements.size() - 1) {
                System.out.print("/");
            }
        }
        System.out.println("");
    }

    public void showSimpleSkill() {
        System.out.println("Skill : " + name);
        System.out.println("BP/ML : " + basePower + "/" + masteryLevel);
        System.out.print("EL    : ");
        for (int i = 0; i < listElements.size(); i++) {
            System.out.print(listElements.get(i));
            if (i < listElements.size() - 1) {
                System.out.print("/");
            }
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
        Skill skill = (Skill) o;
        return Objects.equals(name, skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, basePower, masteryLevel, listElements);
    }

    public Image getSprite(Double rw, Double rh){
        if(this.getMasteryLevel()==1){
            return new Image("./main/resources/mastery_1.png",rw,rh,false,false);
        } else if(this.getMasteryLevel()==1){
            return new Image("./main/resources/mastery_2.png",rw,rh,false,false);
        } else{
            return new Image("./main/resources/mastery_3.png",rw,rh,false,false);
        }
    }


    @Override
    public int compareTo(Skill o) {
        /** Membandingkan Base Power Skill */
        return this.basePower - o.basePower;
    }

}
