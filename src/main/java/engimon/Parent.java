package main.java.engimon;

// import statements 
import java.util.*;

public class Parent{
    /* FIELDS */
    private List<String> name = new ArrayList<>();
    private List<String> species = new ArrayList<>();
    public boolean isParent;

    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Parent() {
        this.isParent = false;
    }
    // ctor dengan parent
    public Parent(String papaName, String papaSpecies, String mamaName, String mamaSpecies) {
        this.name.add(papaName);
        this.name.add(mamaName);
        this.species.add(papaSpecies);
        this.species.add(mamaSpecies);
        this.isParent = true;
    }
    // cctor
    public Parent(Parent parent) {
        if (parent.isParent) {
            this.name.add(parent.name.get(0));
            this.name.add(parent.name.get(1));
            this.species.add(parent.species.get(0));
            this.species.add(parent.species.get(1));
            this.isParent = true;
        }
        else {
            this.isParent = false;
        }
    }

    /* METHODS */
    // print parent di cli
    public void printParent() {
        if (this.isParent) {
            System.out.printf("| %s - %s | %s - %s |\n", this.name.get(0), this.species.get(0), this.name.get(1), this.species.get(1));
        }
        else {
            System.out.println("-");
        }
    }
}