package main.java.engimon.test;

import main.java.engimon.*;
import main.java.element.Element;
import java.util.List;

/** langkah compile dan run:
 * javac main/java/engimon/test/Test_Engimon.java
 * java main.java.engimon.test.Test_Engimon
 */

public class Test_Engimon {
    public static void main(String[] args) {
        // Parent par = new Parent("papa", "laki-laki", "mama", "perempuan");
        // Engimon awewe = new Melt("andru", 135, par);
        // awewe.printEngimon();

        // Engimon e2 = new Cryo("Nerd", 3);
        // e2.printEngimon();

        // Engidex Initialization test 
        // try {
        //     Engimon e3 = Engidex.getBaseEngimon(Element.Fire);
        //     e3.printEngimon();
        // } catch (Exception e){
        //     System.out.println(e);
        // }

        // try {
        //     Engidex.initEngidex();
        //     Engimon e3 = Engidex.getBaseEngimon(Element.Fire);
        //     e3.printEngimon();
        // } catch (Exception e){
        //     System.out.println(e);
        // }
        
        // Engidex is initialized 
        Engidex.initEngidex();

        // Check Engimon with multiple element from Engidex
        try {
            Engimon e3 = Engidex.getEngimonByElement(Element.Fire, Element.Ground);
            e3.printEngimon();
        } catch (Exception e){
            System.out.println(e);
        }

        // Check list of Engimon from Engidex
        try {
            
            List<Engimon> e3 = Engidex.getEngimonByElement(Element.Fire);
            e3.forEach(e -> e.printEngimon());
        } catch (Exception e){
            System.out.println(e);
        }
        
    }

}
