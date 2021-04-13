package main.java.engimon;

public class Melt extends Engimon {
    Melt( String name ){
        this.name = name;
    }

    Melt ( String name, Parent parent ){
        this.name = name;
        this.parent = parent;
    }
}
