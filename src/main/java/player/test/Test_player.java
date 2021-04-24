package main.java.player.test;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import main.java.engimon.Engimon;
import main.java.engimon.species.Cryo;
import main.java.engimon.species.HydroCrystallize;
import main.java.engimon.species.Pyro;
import main.java.inventory.Skill_Item;
import main.java.player.*;
public class Test_player {
    public static void main(String[] args) {
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
        }catch (Exception e){
            System.out.println("Something Went Wrong");
        }

        test.getInventory().printInventory();

        Skill_Item item = new Skill_Item();

//        try {
//            Player.save(test);
//        }catch (Exception e){
//            System.out.print(e.getMessage());
//        }
//
//        Engimon illegal = new HydroCrystallize("illegal",3);
//        try{
//            test.getInventory().addEngimon(illegal);
//        }catch (Exception e){
//            System.out.println("Something Went Wrong");
//        }
//
//
//
//
//        try {
//            test = Player.load("./playerSaveFile.json");
//            test.getInventory().printInventory();
//        }catch (Exception e){
//            System.out.print(e.getMessage());
//        }



        //System.out.print("wkkw");
    }
}
