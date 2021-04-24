package main.java.exception;

public class NullActiveEngimon extends Exception{
    static final long serialVersionUID = 1L; // Idk if this is nescessary

    public NullActiveEngimon(){
        super("Engimon Activenya Ded");
    }
    public NullActiveEngimon(String errorMessage){
        super(errorMessage);
    }
}
