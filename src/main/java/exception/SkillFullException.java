package main.java.exception;

public class SkillFullException extends Exception{
    public SkillFullException(){
        super("Seluruh slot skill Engimon sudah dipakai");
    }
}
