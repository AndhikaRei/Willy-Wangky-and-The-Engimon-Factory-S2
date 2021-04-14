package main.java.engimon.test;

import main.java.engimon.*;
import main.java.engimon.species.*;
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
        
        try {
            // Check Engimon with multiple element from Engidex
            System.out.println("--- CHECK ENGIMON WITH FIRE AND GROUND ELEMENT ---");
            Engimon e3 = Engidex.getEngimonByElement(Element.Fire, Element.Ground);
            e3.printEngimon();

            // Check list of Engimon from Engidex
            System.out.println("--- LIST OF FIRE ELEMENT ENGIMON ---");
            List<Engimon> e4 = Engidex.getEngimonByElement(Element.Fire);
            e4.forEach(e -> e.printEngimon());

            // Clone an engimon 
            Engimon e5 = e3.cloneEngimon();
            // e5.printEngimon();

            Engimon e6 = e3;
            // e6.printEngimon();

            Parent par = new Parent("papa", "laki-laki", "mama", "perempuan");
            Engimon awewe = new Melt("andru", 135, par);
            awewe.printEngimon();
            
            System.out.println("--- TEST CLONE ---");
            Engimon e7 = awewe.cloneEngimon();
            Engimon e8 = awewe;

            awewe.setExp(200);
            e7.printEngimon();
            e8.printEngimon();

            
        } catch (Exception e){
            System.out.println(e);
        }
        
    }

}
