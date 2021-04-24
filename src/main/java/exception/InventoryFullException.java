package main.java.exception;

public class InventoryFullException extends Exception{
    public InventoryFullException(){
        super("Inventory Penuh");
    }
}
