package main.java.engimon;

// import statements 
import java.util.*;

public class Parent{
    /* FIELDS */
    private List<String> name;
    private List<String> species;

    /* CONSTRUCTORS */
    // ctor tanpa parent
    public Parent() {
        name = new ArrayList<>();
        species = new ArrayList<>();
    }

    // ctor dengan parent
    public Parent(String papaName, String papaSpecies, String mamaName, String mamaSpecies) {
        name = new ArrayList<>();
        species = new ArrayList<>();
        this.name.add(papaName);
        this.name.add(mamaName);
        this.species.add(papaSpecies);
        this.species.add(mamaSpecies);
    }

    
    public Parent( Engimon e1, Engimon e2 ){
        name = new ArrayList<>();
        species = new ArrayList<>();
        String papaName = e1.getName();
        String mamaName = e2.getName();
        String papaSpecies = e1.getSpecies();
        String mamaSpecies = e2.getSpecies();

        this.name.add(papaName);
        this.name.add(mamaName);
        this.species.add(papaSpecies);
        this.species.add(mamaSpecies);
    }
    // cctor
    public Parent(Parent parent) throws IllegalArgumentException {
        name = new ArrayList<>();
        species = new ArrayList<>();
        if (parent instanceof Parent) {
            this.name.add(parent.name.get(0));
            this.name.add(parent.name.get(1));
            this.species.add(parent.species.get(0));
            this.species.add(parent.species.get(1));
        } else {
            throw new IllegalArgumentException("Parent type Copy Constructor argument must be of type Parent");
        }
    }

    /* METHODS */
    public boolean isEmpty(){
        return this.species.size() == 0;
    }

    // print parent di cli
    public void printParent() {
        System.out.printf("| %s - %s | %s - %s |\n", this.name.get(0), this.species.get(0), this.name.get(1), this.species.get(1));
    }
    // Return parent dalam bentuk string
    public String stringParent(){
        if(this.name.isEmpty()){
            return ("No Parent");
        } else {
            return String.format("%s | %s", this.name.get(0), this.name.get(1));
        }

    }
}