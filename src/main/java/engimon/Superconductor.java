package main.java.engimon;

public class Superconductor extends Engimon {
    Superconductor( String name ){
        this.name = name;
    }

    Superconductor( String name, Parent parent ){
        this.name = name;
        this.parent = parent;
    }
}
