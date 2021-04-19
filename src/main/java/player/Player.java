package main.java.player;
import java.util.*;
import main.java.engimon.*;
import main.java.inventory.Inventory;
import main.java.inventory.Skill_Item;
public class Player {
    private Engimon activeEngimon;
    private Inventory<Engimon,Skill_Item> inventoryEntity;

    public Player(){
        this.inventoryEntity = new Inventory<Engimon,Skill_Item>();
        activeEngimon = null;
    }

    public Inventory getInventory(){
        return this.inventoryEntity;
    }

    public void PrintInventory(){
        inventoryEntity.printInventory();
    }
    public void changeActiveEngimon(int i){
        activeEngimon = inventoryEntity.getEngimon(i);
    }
    public Engimon getActiveEngimon(){
        return activeEngimon;
    }
    public void KillActiveEngimon(){
        inventoryEntity.KillEngimon(activeEngimon);
        activeEngimon = null;
    }

}
