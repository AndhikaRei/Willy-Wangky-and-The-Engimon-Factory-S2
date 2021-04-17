package main.java.exception;

public class ItemNotEnoughAmountException extends Exception {
    public ItemNotEnoughAmountException(){
        super("Jumlah item tidak cukup");
    }
}
