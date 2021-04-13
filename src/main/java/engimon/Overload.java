package main.java.engimon;

public class Overload extends Engimon {
    Overload( String name ){
        this.name = name;
    }

    Overload( String name, Parent parent ){
        this.name = name;
        this.parent = parent;
    }
}
