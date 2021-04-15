package main.java.engimon.test;

import java.util.List;

import main.java.element.*;
import main.java.engimon.*;
import main.java.skill.*;

public class Test_Breeding {
    public static void main(String[] args) {
        Engidex.initEngidex();
        
        try {
            Engimon parentA = Engidex.getEngimonBySpecies("Cryo");
            Engimon parentB = Engidex.getEngimonBySpecies("Electro");

            parentA.getSkill().get(0).levelUp();
            parentA.getSkill().get(0).levelUp();
            parentB.getSkill().get(0).levelUp();

            // Aman
            // List<Skill> listSk = Breeding_Fountain.sortingSkills(parentA, parentB);
            // for (Skill sk : listSk) {
            //     sk.showSimpleSkill();
            // }

            Engimon child = Breeding_Fountain.startBreeding(parentA, parentB);
            // Breeding_Fountain.addSkillAnak(child,listSk);

            List<Skill> skillAnak = child.getSkill();
            for (Skill skill : skillAnak) {
                skill.showSimpleSkill();
            }

            // Aman
            if (Breeding_Fountain.isElementSimilar(parentA, parentB)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }

            // Aman
            List<Element> listEl = Breeding_Fountain.sortElementAdv(parentA, parentB);
            for (Element element : listEl) {
                System.out.println(element);
            }




        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
