package main.java.map.test;


import main.java.map.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

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
                }else if(command == 'b'){
                    AtomicInteger x = new AtomicInteger(0);
                    AtomicInteger y = new AtomicInteger(0);
                    Engimon e = m.getNearbyEnemyEngimon(x,y);
                    e.printEngimon();
                }else if(command == 'x'){
                    m.saveMap();
                }else if(command == 't'){
                    m.loadMap("save.txt");
                }else{
                    System.out.println("Command tidak tersedia");;
                }
                if(turn%5==0){
                    m.spawnRandomEngimon(7);
                    System.out.println("A new engimon spawned");
                } if(turn%7==0){
                    m.evolveAllEngimon();
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
