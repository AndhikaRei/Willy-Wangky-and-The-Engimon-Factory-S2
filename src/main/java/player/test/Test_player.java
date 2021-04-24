package main.java.player.test;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import main.java.engimon.Engimon;
import main.java.engimon.species.Cryo;
import main.java.inventory.Skill_Item;
import main.java.player.*;
public class Test_player {
    public static void main(String[] args) {
        Player test = new Player();
        Engimon engimon = new Cryo("wkwk",3);
        test.getInventory().addEngimon(engimon);


        test.getInventory().printInventory();
        Skill_Item item = new Skill_Item();

        try {
            Player.save(test);
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
        try {
            test = Player.load("playerSaveFile.json");
        }catch (Exception e){
            System.out.print(e.getMessage());
        }



        System.out.print("wkkw");
    }
}
