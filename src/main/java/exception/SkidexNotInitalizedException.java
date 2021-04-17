package main.java.exception;

public class SkidexNotInitalizedException extends Exception {
    public SkidexNotInitalizedException(){
        super("Skidex has not been initialized");
    }
}
