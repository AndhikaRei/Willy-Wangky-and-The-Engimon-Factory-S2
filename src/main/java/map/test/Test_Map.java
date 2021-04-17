package main.java.map.test;


import main.java.map.*;
import java.util.Scanner;
import main.java.engimon.*;

public class Test_Map {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Map m = new Map(20, 10, "map.txt");            
        Engidex.initEngidex();
        m.addEngimon(3,3, Engidex.getEngimonBySpecies("Pyro"));
        m.addEngimon(4, 10,  Engidex.getEngimonBySpecies("Hydro"));
        m.addEngimon(7, 2,  Engidex.getEngimonBySpecies("Geo"));
        m.addEngimon(7, 14,  Engidex.getEngimonBySpecies("Melt"));
        m.printMap();
        int turn = 1;
        System.out.println("Turn: "+turn);
        System.out.println("Total wild engimon on map: "+m.getWildEngimonPosition().size());
        turn++;
        char command = sc.next().charAt(0);
        while(command != 'e'){
            try{
                if(command == 'w'){
                    m.move('w');
                }else if(command == 'a'){
                    m.move('a');
                }else if(command == 's'){
                    m.move('s');
                }else if(command == 'd'){
                    m.move('d');
                }else{
                    System.out.println("Command tidak tersedia");;
                }
                if(turn%5==0){
                    m.spawnRandomEngimon(8);
                    System.out.println("A new engimon spawned");
                }
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Turn: "+turn);
            System.out.println("Total wild engimon on map: "+m.getWildEngimonPosition().size());
            turn++;
            sc = new Scanner(System.in);
            command = sc.next().charAt(0);
        }
    }
}
