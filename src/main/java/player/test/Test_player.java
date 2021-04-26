package main.java.player.test;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import main.java.engimon.Engimon;
import main.java.engimon.species.Cryo;
import main.java.engimon.species.HydroCrystallize;
import main.java.engimon.species.Pyro;
import main.java.inventory.Skill_Item;
import main.java.player.*;
import main.java.skill.Skidex;
public class Test_player {
    public static void main(String[] args) {
        Skidex.initSkill();
        Player test = new Player();
        Engimon neula = new Cryo("neula",3);
        Engimon diluc = new Pyro("dulic",3);
        Engimon bennet = new Pyro("bennet",3);
        bennet.addExp(10000);
        Engimon  whotau = new Pyro("hutao",3);
        whotau.addExp(10000);
        Engimon chongyun = new Cryo("Chongyun",3);
        chongyun.addExp(10000);
        try{
            test.getInventory().addEngimon(diluc);
            test.getInventory().addEngimon(neula);
            test.getInventory().addEngimon(bennet);
            test.getInventory().addEngimon(whotau);
            test.getInventory().addEngimon(chongyun);
            test.setActiveEngimon(null);
            test.getInventory().addItem(new Skill_Item(Skidex.getSkillByName("Fire Breath"),5));
            test.getInventory().addItem(new Skill_Item(Skidex.getSkillByName("Rupture"),10));
        }catch (Exception e){
            System.out.println("Something Went Wrong");
        }
        test.getInventory().printInventory();
        test.savePlayer();
        System.out.println("================================================================================");
        Player test2 = new Player();
        try{
            test2.loadPlayer("savePlayer.txt");
        }catch (Exception e){
            e.printStackTrace();
        }
        test2.getInventory().printInventory();
        System.out.println(test2.getActiveEngimon());
        System.out.println("Loaded");
    }
}
