package main.java.exception;

public class InvalidEngimonLevelException extends Exception {
    public InvalidEngimonLevelException() {
        super("Level Engimon tidak cukup untuk melakukan Breeding");
    }
}
