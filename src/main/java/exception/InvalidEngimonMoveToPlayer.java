package main.java.exception;

public class InvalidEngimonMoveToPlayer extends Exception {
    static final long serialVersionUID = 1L; // Idk if this is nescessary

    public InvalidEngimonMoveToPlayer(){
        super("Engimon liar tidak bisa bergerak ke posisi Player. Melakukan reposisi engimon liar");
    }
    public InvalidEngimonMoveToPlayer(String errorMessage){
        super(errorMessage);
    }
}
