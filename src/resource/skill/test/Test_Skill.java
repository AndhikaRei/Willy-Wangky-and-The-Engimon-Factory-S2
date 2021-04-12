package resource.skill.test;

import java.util.List;

import resource.element.*;
import resource.skill.*;


public class Test_Skill {
    public static void main(String[] args) {
        Skidex.initSkill();

        Skill s1 = new Skill("Fire Breath", "Hah Naga!", 20, Element.Fire);
        Skill s2 = new Skill(s1);

        s1.levelUp();
        s1.levelUp();
        s1.levelUp();
        if (s1.equals(s2)) {
            System.out.println("Yes");
        }
        System.out.println(s1.totalDamage());
        s1.showSkill();
        s2.showSkill();

        List<Skill> listSk = Skidex.getCompatibleSkill(Element.Water);
        for (Skill sk : listSk) {
            sk.showSimpleSkill();
        }
        
    }
}
