package main.resource.skill;

import main.resource.element.Element;

public class Test_Skill {
    public static void main(String[] args) {
        Skill s1 = new Skill("Fire Breath", "Hah Naga!", 20, Element.Fire, null);
        Skill s2 = new Skill("Fire Breath", "Hah Naga!", 20, Element.Fire, null);

        s1.levelUp();
        s1.levelUp();
        s1.levelUp();
        if (s1.equals(s2)) {
            System.out.println("Yes");
        }
        System.out.println(s1.totalDamage());
        s1.showSkill();
        
    }
}
