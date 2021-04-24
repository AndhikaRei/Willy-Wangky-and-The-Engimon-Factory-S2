package main.java.player;
import java.io.*;
import java.util.*;
import main.java.engimon.*;
import main.java.engimon.species.*;
import main.java.exception.ItemNotEnoughAmountException;
import main.java.exception.SkillElementNotCompatibleException;
import main.java.inventory.Inventory;
import main.java.inventory.Skill_Item;
import com.google.gson.Gson;
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

//    public Player(String JsonFIle ){
//        java.net.URL url = this.getClass().getResource(JsonFIle);
//        File jsonFile = new File(url.getFile());
//        System.out.println("Full path of file: " + jsonFile);
//
//        try{
//            BufferedReader br = new BufferedReader(new FileReader(JsonFIle));
//            Player temp = new Gson().fromJson(br, Player.class);
//            this = temp;
//        }catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }



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
    public void renameEngimon(int i, String Name){
        inventoryEntity.getEngimon(i).setName(Name);
    }

    public void save() throws Exception {
        Gson gson = new Gson();
        String filePath = "./";
        gson.toJson(this, new FileWriter(filePath));
        System.out.println(new Gson().toJson(this));
    }

    public static void save(Player player)throws Exception{
        Gson gson = new Gson();
        String filePath = "./";
        gson.toJson(player, new FileWriter(filePath));
        System.out.println(new Gson().toJson(player));
    }

    public static Player load(String JsonFIle )throws IOException{
        java.net.URL url = JsonFIle.getClass().getResource(JsonFIle);
        File jsonFile = new File(url.getFile());
        System.out.println("Full path of file: " + jsonFile);

        try{
            BufferedReader br = new BufferedReader(new FileReader(JsonFIle));
            Player temp = new Gson().fromJson(br, Player.class);
            return temp;
        }catch (IOException e)
        {
            e.printStackTrace();
            throw e;
        }

    }

}
