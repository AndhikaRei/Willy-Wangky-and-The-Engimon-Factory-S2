package main.java.engimon;

public class Frozen extends Engimon {
    Frozen( String name ){
        this.name = name;
    }

    Frozen( String name, Parent parent ){
        this.name = name;
        this.parent = parent;
    }
}
