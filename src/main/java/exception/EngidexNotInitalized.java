package main.java.exception;

public class EngidexNotInitalized extends Exception {
    static final long serialVersionUID = 1L; // Idk if this is nescessary

    public EngidexNotInitalized(){
        super("Engidex has not been initialized");
    }
    public EngidexNotInitalized(String errorMessage){
        super(errorMessage);
    }
}
