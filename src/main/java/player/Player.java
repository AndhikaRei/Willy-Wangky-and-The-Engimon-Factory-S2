package main.java.player;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

import main.java.element.Element;
import main.java.engimon.*;
import main.java.engimon.species.*;
import main.java.exception.ItemNotEnoughAmountException;
import main.java.exception.SkillElementNotCompatibleException;
import main.java.inventory.Inventory;
import main.java.inventory.Skill_Item;
import com.google.gson.Gson;
import main.java.exception.*;
import main.java.skill.Skidex;
import main.java.skill.Skill;

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
        a1.setLevel(40);
        a2.setLevel(40);
        a3.setLevel(40);
        a4.setLevel(40);
        a5.setLevel(40);
        try{
            // a1.addSkill(new Skill("Fire Breath", "Hah Naga!", 20, Element.Fire));
            // a2.addSkill(new Skill("Gush", "Ciuhh!", 20, Element.Water));
            this.inventoryEntity.addEngimon(a2);
            this.inventoryEntity.addEngimon(a1);
            this.inventoryEntity.addEngimon(a3);
            this.inventoryEntity.addEngimon(a4);
            this.inventoryEntity.addEngimon(a5);
        }catch (Exception e){
            System.out.println("Something went Wrong");
        }

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

    public void ReleaseEngimon(Integer i){
        if(this.getInventory().getEngimons().get(i).equals(this.activeEngimon)){
            this.activeEngimon = null;
        }
        this.getInventory().deleteEngimon(i);
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
        Integer isDead = inventoryEntity.KillEngimon(activeEngimon);
        if (isDead == 1){
            this.activeEngimon = null;
        }
    }
    public void renameEngimon(int i, String Name){
        inventoryEntity.getEngimon(i).setName(Name);
    }
    public void setActiveEngimon(Engimon e){
        this.activeEngimon = e;
    }

//    public void save() throws Exception {
//        Gson gson = new Gson();
//        String filePath = "";
//        gson.toJson(this, new FileWriter(filePath));
//        System.out.println(new Gson().toJson(this));
//    }

    public static void save(Player player)throws Exception{
        Gson gson = new Gson();
        String filePath = "./wkkw.json";
        gson.toJson(player, new FileWriter(filePath));
        System.out.println(new Gson().toJson(player));

        try {
            File myObj = new File("./playerSaveFile.json");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("./playerSaveFile.json");
            myWriter.write(new Gson().toJson(player));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void savePlayer(){
        try {
            FileWriter myWriter = new FileWriter("src\\main\\resources\\player.txt");
            String textToSave;
            StringBuilder sb = new StringBuilder();
            // Save Engimon in Inventory
            String activeName = this.getActiveEngimon().getName();
            int indexActive = 0, i = 0;
            List<Engimon> listEngi = this.inventoryEntity.getEngimons();
            sb.append("Total Engimon: ").append(listEngi.size()).append('\n');
            for(Engimon e : listEngi){
                if(e.getName().equals(activeName)){
                    indexActive = i;
                }
                sb.append(e.getSpecies()).append('\n')
                    .append(e.getName()).append('\n')
                    .append(e.getLives()).append('\n')
                    .append(e.getParent().bothParent()).append("\n{");
                List<Skill> liSkill = e.getSkill();
                for(Skill skill : liSkill){
                    sb.append('(').append(skill.getName()).append(',').append(skill.getMasteryLevel()).append(");");
                }
                sb.append("}\n").append(e.getLevel()).append('\n')
                    .append(e.getExp()).append('\n')
                    .append(e.getCumulExp()).append('\n');
                i++;
            }
            sb.append("Active engimon index : ").append(indexActive).append('\n');
            List<Skill_Item> listSkill = this.inventoryEntity.getItems();
            sb.append("Total Skill Item: ").append(listSkill.size()).append('\n');
            for(Skill_Item si : listSkill){
                sb.append(si.getSkillName()).append(',').append(si.getAmount()).append('\n');
            }
            textToSave = sb.toString();
            myWriter.write(textToSave);
            myWriter.close();
            System.out.println("Successfully wrote player to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static Player load(String JsonFIle )throws IOException   {

        //java.net.URL url = JsonFIle.getClass().getResource(JsonFIle);
        try{
            File jsonFile = new File(JsonFIle);
            System.out.println("Full path of file: " + jsonFile);
        }catch (Exception e){
            System.out.println("File Not Found");
            throw e;
        }
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

    public void loadPlayer(String txt) throws Exception{
        int i, j, totalEngimons=0, totalItems=0;
        Engidex.initEngidex();
        Skidex.initSkill();
        i = 0;
        j = 0;
        String filename = "src\\main\\resources\\".concat(txt);
        File file=new File(filename);
        Scanner sc = new Scanner(file);
        Engimon newEngimon = Engidex.getEngimonBySpecies("Pyro").cloneEngimon(); //sample
        List<Engimon> newEngimons = new ArrayList<Engimon>();
        List<Skill_Item> newSkills = new ArrayList<Skill_Item>();
        while(sc.hasNextLine()){
            String currLine = sc.nextLine();
            if(i==0){
                totalEngimons = Integer.parseInt(currLine.substring(15));
            }else if(i<(totalEngimons*8+1)){
                if(i%8==1){
                    newEngimon = Engidex.getEngimonBySpecies(currLine).cloneEngimon();
                    System.out.println(newEngimon.getName());
                }else if(i%8==2){
                    newEngimon.setName(currLine);
                }else if(i%8==3){
                    newEngimon.setLives(Integer.parseInt(currLine));
                }else if(i%8==4){
                    if(!currLine.equals("No Parent")){
                        String parent = currLine;
                        List<String> list = new ArrayList<String>(Arrays.asList(parent.split(",")));
                        System.out.println(list);
                        Parent newParent = new Parent(list.get(0), list.get(1), list.get(2), list.get(3));
                        newEngimon.setParent(newParent);
                    }
                }else if(i%8==5){
                    List<Skill> engiSkill = new ArrayList<Skill>();
                    while(!currLine.equals(";}")){
                        String name = currLine.substring(currLine.indexOf('(')+1, currLine.indexOf(','));
                        int mastery = Integer.parseInt(currLine.substring(currLine.indexOf(',')+1, currLine.indexOf(')')));
                        if(name.equals(newEngimon.getSkill().get(0).getName())){
                            Skill newSkill = newEngimon.getSkill().get(0);
                            newSkill.setMasteryLevel(mastery);
                            engiSkill.add(newSkill);
                        }else{
                            Skill newSkill = Skidex.getSkillByName(name);
                            newSkill.setMasteryLevel(mastery);
                            engiSkill.add(newSkill);
                        }
                        currLine = currLine.substring(currLine.indexOf(';'));
                    }
                    newEngimon.setSkill(engiSkill);
                }else if(i%8==6){
                    newEngimon.setLevel(Integer.parseInt(currLine));
                }else if(i%8==7){
                    newEngimon.setExp(Integer.parseInt(currLine));
                }else if(i%8==0){
                    newEngimon.setCumulExp(Integer.parseInt(currLine));
                    newEngimons.add(newEngimon);
                }
            }else if(i==(totalEngimons*8+1)){
                this.inventoryEntity.setEngimons(newEngimons);
                int index= Integer.parseInt(currLine.substring(23));
                changeActiveEngimon(index);
            }else if(i==(totalEngimons*8+2)){
                totalItems = Integer.parseInt(currLine.substring(18));
            }else{
                String name = currLine.substring(0, currLine.indexOf(','));
                int amount = Integer.parseInt(currLine.substring(currLine.indexOf(',')+1));
                Skill_Item si = new Skill_Item(Skidex.getSkillByName(name), amount);
                newSkills.add(si);
            }
            i++;
        }
        this.inventoryEntity.setItems(newSkills);
        System.out.println("Loaded player from player.txt");
    }
}
