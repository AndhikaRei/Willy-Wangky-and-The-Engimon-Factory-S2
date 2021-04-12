package main.java.inventory.test;

import main.java.inventory.*;

import java.util.ArrayList;
import java.util.List;

import main.java.element.*;
import main.java.skill.Skill;

public class Test_Skill_Item {
    public static void main(String[] args) {
        Skill s1 = new Skill("Fire Breath", "Hah Naga!", 20, Element.Fire, null);
        Skill_Item it1 = new Skill_Item(s1);

        s1.levelUp();
        Skill s2 = it1.learn(Element.Fire);
        s2.levelUp();
        it1.showSimpleItem();
        
        System.out.println(s2);

        List<Skill> ls = new ArrayList<>();
        System.out.println(s1);
        ls.add(new Skill(s1));
        s1.levelUp();
        Skill s5 = ls.get(0);
        System.out.println(s5);
    }
    

    
}
