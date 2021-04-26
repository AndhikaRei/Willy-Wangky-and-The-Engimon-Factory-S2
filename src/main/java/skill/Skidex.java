package main.java.skill;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.element.Element;
import main.java.exception.*;

public class Skidex implements Iterable<Skill> {
    private static List<Skill> listSkill = new ArrayList<>();
    
    public static List<Skill> getCompatibleSkill(Element el) throws SkidexNotInitalizedException {
        if (listSkill.isEmpty()) {
            throw new SkidexNotInitalizedException();
        }

        List<Skill> compatibleSkill = new ArrayList<>();
        for (Skill sk : listSkill) {
            if (sk.isElementCompatible(el)) {
                compatibleSkill.add(new Skill(sk));
            }
        }
        return compatibleSkill;
    }

    public static List<Skill> getCompatibleSkill(List<Element> listEl) throws SkidexNotInitalizedException {
        if (listSkill.isEmpty()) {
            throw new SkidexNotInitalizedException();
        }
        
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
        
        /* Basic Skill */
        listSkill.add(new Skill("Freeze!", "I'll never use my dad's power.", 20, Element.Ice));
        listSkill.add(new Skill("Golem Es!", "Ez tank 4.", 20, Element.Ground, Element.Ice));
        listSkill.add(new Skill("Thunderstorm", "Lightning goes brrr.", 20, Element.Electric));
        listSkill.add(new Skill("Charged Water!", "Hati-hati kesetrum.", 20, Element.Water, Element.Electric));
        listSkill.add(new Skill("Golem Petir!", "Ez tank 3.", 20, Element.Electric, Element.Ground));
        listSkill.add(new Skill("Let it go~", "Elsa approved.", 20, Element.Water, Element.Ice));
        listSkill.add(new Skill("Tanah tinggi!", "Ez cover.", 20, Element.Ground));
        listSkill.add(new Skill("Purification!", "Aqua approved.", 20, Element.Water));
        listSkill.add(new Skill("Golem Air!", "Ez tank 2.", 20, Element.Water, Element.Ground));
        listSkill.add(new Skill("Half-Cold Half-Hot", "Todoroki not approve.", 20, Element.Fire, Element.Ice));
        listSkill.add(new Skill("Korsleting", "Api berpadu dengan Listrik", 20, Element.Fire, Element.Electric));
        listSkill.add(new Skill("Explosion!", "Megumin approved.", 20, Element.Fire));
        listSkill.add(new Skill("Golem Api!", "Ez tank.", 20, Element.Fire, Element.Ground));
        listSkill.add(new Skill("Atta Halilintar", "Masa listrik bisa berpadu dengan es?", 20, Element.Electric, Element.Ice));
        listSkill.add(new Skill("Indomie rebus", "Masak aer.", 20, Element.Fire, Element.Water));
    }

    @Override
    public Iterator<Skill> iterator() {
        return Skidex.listSkill.iterator();
    }

    public static Skill getSkillByName(String skillName){
        Skill result = null;
        for(Skill skill : listSkill){
            if(skill.getName().equals(skillName)){
                try {
                    result = skill.cloneSkill();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return result;
    }

}
