package main.java.engimon.test;

import main.java.engimon.*;
import main.java.engimon.species.*;
import main.java.element.Element;
import java.util.List;

// For testing Engidex 
import java.util.Map;

/** langkah compile dan run:
 * javac main/java/engimon/test/Test_Engimon.java
 * java main.java.engimon.test.Test_Engimon
 */

public class Test_Engimon {
    public static void main(String[] args) {
        // Parent par = new Parent("papa", "laki-laki", "mama", "perempuan");
        // Engimon awewe = new Melt("andru", 135, par);
        // awewe.print();

        // Engimon e2 = new Cryo("Nerd", 3);
        // e2.print();

        // Engidex Initialization test 
        // try {
        //     Engimon e3 = Engidex.getBaseEngimon(Element.Fire);
        //     e3.print();
        // } catch (Exception e){
        //     System.out.println(e);
        // }

        // try {
        //     Engidex.initEngidex();
        //     Engimon e3 = Engidex.getBaseEngimon(Element.Fire);
        //     e3.print();
        // } catch (Exception e){
        //     System.out.println(e);
        // }
        
        // Engidex is initialized 
        Engidex.initEngidex();
        
        try {
            // Check Engimon by Species
            System.out.println("--- CHECK ENGIDEX BY SPECIES ---");
            Engimon e1 = Engidex.getEngimonBySpecies("Electro");
            e1.print();
            System.out.println("--- CHECK ENGIDEX ---");
            Map<String, Engimon> EngidexList = Engidex.getEngidex();
            for (Map.Entry<String,Engimon> entry : EngidexList.entrySet()){
                System.out.println("Key = " + entry.getKey()  + ", Value = ");
                entry.getValue().print();
            }
            System.out.println("--- CHECK ENGIDEX ENDS ---");
            Engimon e2 = Engidex.getEngimonBySpecies(e1.getSpecies());
            e2.print();

            // Check Engimon with multiple element from Engidex
            System.out.println("--- CHECK ENGIMON WITH FIRE AND GROUND ELEMENT ---");
            Engimon e3 = Engidex.getEngimonByElement(Element.Fire, Element.Ground);
            e3.print();

            // Check list of Engimon from Engidex
            System.out.println("--- LIST OF FIRE ELEMENT ENGIMON ---");
            List<Engimon> e4 = Engidex.getEngimonByElement(Element.Fire);
            e4.forEach(e -> e.print());

            // Clone an engimon 
            Engimon e5 = e3.cloneEngimon();
            // e5.print();

            Engimon e6 = e3;
            // e6.print();

            Parent par = new Parent("papa", "laki-laki", "mama", "perempuan");
            Engimon awewe = new Melt("andru", 135, par);
            awewe.print();
            
            System.out.println("--- TEST CLONE ---");
            Engimon e7 = awewe.cloneEngimon();
            Engimon e8 = awewe;

            awewe.setExp(200);
            e7.print();
            e8.print();

            
        } catch (Exception e){
            System.out.println(e);
        }
        
    }

}
