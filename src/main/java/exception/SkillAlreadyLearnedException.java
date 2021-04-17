package main.java.exception;

public class SkillAlreadyLearnedException extends Exception {
    public SkillAlreadyLearnedException(){
        super("Skill sudah dipelajari oleh Engimon");
    }
}
