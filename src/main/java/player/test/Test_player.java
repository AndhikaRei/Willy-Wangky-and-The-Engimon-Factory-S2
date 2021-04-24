package main.java.player.test;
import main.java.player.*;
public class Test_player {
    public static void main(String[] args) {
        Player test = new Player();
        try {
            Player.save(test);
        }catch (Exception e){
            System.out.print(e.getMessage());
        }



        System.out.print("wkkw");
    }
}
