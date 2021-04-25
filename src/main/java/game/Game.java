package main.java.game;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;
import main.java.battle.*;
import main.java.element.*;
import main.java.engimon.*;
import main.java.exception.*;
import main.java.element.*;
import main.java.game.*;
import main.java.inventory.*;
import main.java.map.*;
import main.java.player.*;
import main.java.skill.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Game implements Serializable {
    private Map map;
    private Player player;

    public Game() throws Exception{
        this.map = new Map(20, 10, "map.txt");
        this.player = new Player();     
    }

    public Map getMap(){
        return this.map;
    }
    
    public Player getPlayer(){
        return this.player;
    }

    public void save(){
        try (FileOutputStream f = new FileOutputStream("src\\main\\resources\\save.txt");
        ObjectOutputStream s = new ObjectOutputStream(f)){
            this.writeToFile(s);
        }catch(IOException error){
            error.printStackTrace();
        }
    }

    public void writeToFile(ObjectOutputStream out) throws IOException{
        out.writeObject(this);
        out.close();
    }

    private void load() throws IOException{
        Game loadedGame = loadFile();
        this.map = loadedGame.map;
        this.player = loadedGame.player;
    }

    private Game loadFile() throws IOException{
        Game loadedGame = null;
        try (FileInputStream input = new FileInputStream("src\\main\\resources\\save.txt");
        ObjectInputStream s = new ObjectInputStream(input)){
            loadedGame = (Game) s.readObject();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("Loaded previous game successfully!");
        return loadedGame;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Game game = new Game();     
        Engidex.initEngidex();
        game.map.addEngimon(3,3, Engidex.getEngimonBySpecies("Pyro"));
        game.map.addEngimon(4, 10,  Engidex.getEngimonBySpecies("Hydro"));
        game.map.addEngimon(7, 2,  Engidex.getEngimonBySpecies("Geo"));
        game.map.addEngimon(7, 14,  Engidex.getEngimonBySpecies("Melt"));
        game.map.printMap();
        int turn = 1;
        System.out.println("Turn: "+turn);
        System.out.println("Total wild engimon on map: "+game.map.getWildEngimonPosition().size());
        turn++;
        char command = sc.next().charAt(0);
        while(command != 'e'){
            try{
                if(command == 'w'){
                    game.map.move('w');
                }else if(command == 'a'){
                    game.map.move('a');
                }else if(command == 's'){
                    game.map.move('s');
                }else if(command == 'd'){
                    game.map.move('d');
                }else if(command == 'b'){
                    AtomicInteger x = new AtomicInteger(0);
                    AtomicInteger y = new AtomicInteger(0);
                    Engimon e = game.map.getNearbyEnemyEngimon(x,y);
                    e.print();
                }else if(command == 'x'){
                    game.save();
                }else if(command == 't'){
                    game.load();
                }else{
                    System.out.println("Command tidak tersedia");;
                }
                if(turn%5==0){
                    game.map.spawnRandomEngimon(7);
                    System.out.println("A new engimon spawned");
                } if(turn%7==0){
                    game.map.evolveAllEngimon();
                }
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Turn: "+turn);
            System.out.println("Total wild engimon on map: "+game.map.getWildEngimonPosition().size());
            turn++;
            sc = new Scanner(System.in);
            command = sc.next().charAt(0);
        }
    } 

}
