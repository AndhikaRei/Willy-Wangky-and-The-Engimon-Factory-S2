package main.java.player;
import java.util.*;
import main.java.engimon.*;
import main.java.engimon.species.*;
import main.java.inventory.Inventory;
import main.java.inventory.Skill_Item;

public class Player {
    private Engimon activeEngimon;
    private Inventory<Engimon,Skill_Item> inventoryEntity;

    public Player(){
        this.inventoryEntity = new Inventory<Engimon,Skill_Item>();
        Engimon a1 = new Pyro("Pyro1",3);
        Engimon a2 = new Hydro("Hydro1",3);
        Engimon a3 = new Electro("Electro1",3);
        Engimon a4 = new Geo("Geo1",3);
        Engimon a5 = new Cryo("Cryo1",3);

        this.inventoryEntity.addEngimon(a1);
        this.inventoryEntity.addEngimon(a2);
        this.inventoryEntity.addEngimon(a3);
        this.inventoryEntity.addEngimon(a4);
        this.inventoryEntity.addEngimon(a5);

        this.activeEngimon = this.inventoryEntity.getEngimon(0);
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
