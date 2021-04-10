package main.resource.inventory.test;

import main.resource.inventory.*;
import main.resource.element.*;
import main.resource.skill.Skill;

public class Test_Skill_Item {
    public static void main(String[] args) {
        Skill s1 = new Skill("Fire Breath", "Hah Naga!", 20, Element.Fire, null);
        Skill_Item it1 = new Skill_Item(s1);

        s1.levelUp();
        it1.showSimpleItem();
    }
    

    
}
