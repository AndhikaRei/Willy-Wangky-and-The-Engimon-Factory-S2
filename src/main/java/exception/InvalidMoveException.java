package main.java.exception;

public class InvalidMoveException extends Exception {
    static final long serialVersionUID = 1L; // Idk if this is nescessary

    public InvalidMoveException(){
        super("Anda tidak bisa bergerak ke sana");
    }
    public InvalidMoveException(String errorMessage){
        super(errorMessage);
    }
}
