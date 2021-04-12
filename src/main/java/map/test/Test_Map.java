package main.java.map.test;


import main.java.map.*;
import java.util.Scanner;


public class Test_Map {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Map m = new Map(20, 10, "map.txt");            
        m.addEngimon(3,3, "Pyro");
        m.addEngimon(4, 10, "Hydro");
        m.addEngimon(7, 2, "Geo");
        m.addEngimon(7, 14, "Melt");
        m.printMap();
        char command = sc.next().charAt(0);
        while(command != 'e'){
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
            sc = new Scanner(System.in);
            command = sc.next().charAt(0);
        }
    }
}
