package resource.skill;

import java.util.ArrayList;
import java.util.List;

import resource.element.Element;

public class Skidex {
    private static List<Skill> listSkill = new ArrayList<>();

    // public Skidex() {
    //     listSkill = new ArrayList<Skill>();
    //     initSkill();
    // }
    
    public static List<Skill> getCompatibleSkill(Element el) {
        List<Skill> compatibleSkill = new ArrayList<>();
        for (Skill sk : listSkill) {
            if (sk.isElementCompatible(el)) {
                compatibleSkill.add(new Skill(sk));
            }
        }
        return compatibleSkill;
    }

    public static List<Skill> getCompatibleSkill(List<Element> listEl) {
        List<Skill> compatibleSkill = new ArrayList<>();
        for (Skill sk : listSkill) {
            if (sk.isElementCompatible(listEl)) {
                compatibleSkill.add(new Skill(sk));
            }
        }
        return compatibleSkill;
    }


    public static void initSkill() {
        listSkill.clear();

        List<Element> allElements = new ArrayList<>();
        /* All Elements Skill */
        listSkill.add(new Skill("Punch", "Pukulan Maut!",10,allElements)); 
        listSkill.add(new Skill("Kick", "Sikat Miring!",10,allElements));
        listSkill.add(new Skill("ONE-PUNCH", "Hiyaa jurus si botak", 20, allElements));

        /* 1 Element Skill */
        listSkill.add(new Skill("Fire Breath", "Hah Naga!", 20, Element.Fire));
        listSkill.add(new Skill("Zoroaster", "Api keabadian", 30, Element.Fire));
        listSkill.add(new Skill("Gush", "Ciuhh!", 20, Element.Water));
        listSkill.add(new Skill("Ravage", "Tentakel + Anime", 30, Element.Water));
        listSkill.add(new Skill("Ball Lighting", "Lookin for me?", 20, Element.Electric));
        listSkill.add(new Skill("Static Storm", "I'm ecstatic!",30 ,Element.Electric));
        listSkill.add(new Skill("Fissure", "Feel the earth shake!", 20, Element.Ground));
        listSkill.add(new Skill("Echo Slam", "1 Million Dollar Slam", 30, Element.Ground));
        listSkill.add(new Skill("Ice Path", "Beku broh", 20, Element.Ice));
        listSkill.add(new Skill("Freezing Field", "Badai salju", 30,Element.Ice));

        /* 2 Element Skill */
        listSkill.add(new Skill("Rupture", "Bocor Bocor", 30, Element.Fire, Element.Water));
        listSkill.add(new Skill("Flamming Lasso", "Kena lo", 30, Element.Fire, Element.Electric));
        listSkill.add(new Skill("Life Break", "Loncat Indah", 30, Element.Fire, Element.Ground));
        listSkill.add(new Skill("Hand of God", "Tangan Madonna", 30, Element.Fire, Element.Ice));
        listSkill.add(new Skill("Chronosphere", "Zawarudo", 30, Element.Water, Element.Electric));
        listSkill.add(new Skill("Feast", "Laperrr",30,Element.Water,Element.Ground));
        listSkill.add(new Skill("Flux", "Ababil", 30, Element.Water, Element.Ice));
        listSkill.add(new Skill("Mana Void", "Duarr", 30, Element.Electric, Element.Ground));
        listSkill.add(new Skill("Heartstopper Aura", "Nyicil Darah", 30, Element.Electric, Element.Ice));
        listSkill.add(new Skill("Dark Ascension", "Stalker in The Night", 30, Element.Ground, Element.Ice));

        // Skill s66 = new Skill("DOOM","JURUS DAJAL",666,allElements);
    }

}
