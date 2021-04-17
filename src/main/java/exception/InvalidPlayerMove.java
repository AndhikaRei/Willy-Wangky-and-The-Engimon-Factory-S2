package main.java.exception;

public class InvalidPlayerMove extends Exception {
    static final long serialVersionUID = 1L; // Idk if this is nescessary

    public InvalidPlayerMove(){
        super("Ada engimon liar disana");
    }
    public InvalidPlayerMove(String errorMessage){
        super(errorMessage);
    }
}
