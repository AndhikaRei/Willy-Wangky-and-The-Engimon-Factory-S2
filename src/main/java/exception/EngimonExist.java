package main.java.exception;

public class EngimonExist extends Exception {
    static final long serialVersionUID = 1L; // Idk if this is nescessary

    public EngimonExist(){
        super("Sudah ada engimon di tiles ini");
    }
    public EngimonExist(String errorMessage){
        super(errorMessage);
    }
}
