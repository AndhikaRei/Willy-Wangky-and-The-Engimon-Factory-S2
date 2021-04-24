package main.java.player;
import java.io.*;
import main.java.*;
import main.java.map.Map;

import java.io.*;

public class Game implements Serializable{
    public Player player;
    public Map map;

    public Game(){
        player = null;
        map = null;
    }
    public void save(){
        try (FileOutputStream f = new FileOutputStream("save.txt");
             ObjectOutputStream s = new ObjectOutputStream(f)) {
            this.writeToFile(s);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Save");
    }

    public void load() throws IOException{
        System.out.println("loading");
        Game loaded = readFile();
        player = loaded.player;
        map = loaded.map;
        //gameOver = loaded.gameOver;
    }

    protected Game readFile() throws IOException{
        Game loadedKirby = null;
        try(FileInputStream in = new FileInputStream("save.txt");
            ObjectInputStream s = new ObjectInputStream(in)){
            loadedKirby = (Game) s.readObject();
        }catch (ClassNotFoundException r){
            r.printStackTrace();
        }
        System.out.println("Loaded");
        return loadedKirby;
    }
    public void writeToFile(ObjectOutputStream out) throws IOException{
        out.writeObject(this);
        out.close();
    }
}
