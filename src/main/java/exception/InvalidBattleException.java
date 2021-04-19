package main.java.exception;

public class InvalidBattleException extends Exception{
    public InvalidBattleException(){
        super ("Tidak ada engimon di sekitar atau anda tidak memiliki active engimon");
    }
}
