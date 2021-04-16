package main.java.engimon;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import main.java.element.Element;
import main.java.engimon.species.*;
import main.java.exception.*;


public class Engidex { 
    private static Map<String, Engimon> Engidex  = new HashMap<String, Engimon>();
    public static void initEngidex(){
        Engimon e1 = new Cryo("Cryo sp.", true);
        Engimon e2 = new CryoCrystallize("CyroCrystallize sp.", 1);
        Engimon e3 = new Electro("Electro sp.", 1);
        Engimon e4 = new ElectroCharged("ElectroCharged sp.", 1);
        Engimon e5 = new ElectroCrystallize("ElectroCrystallize sp.", 1);
        Engimon e6 = new Frozen("Frozen sp.", 1);
        Engimon e7 = new Geo("Geo sp.", 1);
        Engimon e8 = new Hydro("Hydro sp.", 1);
        Engimon e9 = new HydroCrystallize("HydroCrystallize sp.", 1);
        Engimon e10 = new Melt("Melt sp.", 1);
        Engimon e11 = new Overload("Overload sp.", 1);
        Engimon e12 = new Pyro("Pyro sp.", 1);
        Engimon e13 = new PyroCrystallize("PyroCrystallize sp.", 1);
        Engimon e14 = new Superconductor("Superconductor sp.", 1);
        Engimon e15 = new Vaporize("Vaporize sp.", 1);

        e1.setLevel(1);
        e2.setLevel(1);
        e3.setLevel(1);
        e4.setLevel(1);
        e5.setLevel(1);
        e6.setLevel(1);
        e7.setLevel(1);
        e8.setLevel(1);
        e9.setLevel(1);
        e9.setLevel(1);
        e10.setLevel(1);
        e11.setLevel(1);
        e12.setLevel(1);
        e13.setLevel(1);
        e14.setLevel(1);
        e15.setLevel(1);

        Engidex.put("Cryo", e1);
        Engidex.put("CyroCrystallize", e2);
        Engidex.put("Electro", e3);
        Engidex.put("ElectroCharged", e4);
        Engidex.put("ElectroCrystallize", e5);
        Engidex.put("Frozen", e6);
        Engidex.put("Geo", e7);
        Engidex.put("Hydro", e8);
        Engidex.put("HydroCrystallize", e9);
        Engidex.put("Melt", e10);
        Engidex.put("Overload", e11);
        Engidex.put("Pyro", e12);
        Engidex.put("PyroCrystallize", e13);
        Engidex.put("Superconductor", e14);
        Engidex.put("Vaporize", e15);

    }

    public static Map<String, Engimon> getEngidex(){
        return Engidex;
    }

    // Basic Methods
    public static Engimon getEngimonBySpecies(String species) throws Exception{
        if(Engidex.size() == 0){
            throw new EngidexNotInitalized();
        }
        if(Engidex.get(species) == null){
            throw new Exception("Species doesn't exist");
        } else {
            return Engidex.get(species).cloneEngimon();
        }
    }

    // Untuk mengambil engimon dengan hanya 1 element 
    public static Engimon getBaseEngimon(Element element) throws EngidexNotInitalized, CloneNotSupportedException{
        if(Engidex.size() == 0){
            throw new EngidexNotInitalized();
        }

        for( Engimon e : Engidex.values()){
            if( ! e.isOneElement() ) { 
                continue; 
            }
            for( Element el : e.getElement()){
                if(el == element){
                    return e.cloneEngimon();
                }
            }
        }

        // Case Not Found
        return null;
    }

    // Untuk mengambil nama species engimon dengan hanya satu element
    public static String getBaseEngimonSpecies(Element element) throws EngidexNotInitalized, CloneNotSupportedException {
        return getBaseEngimon(element).getSpecies();
    }

    // Untuk mengambil semua engimon bertipe element
    public static List<Engimon> getEngimonByElement(Element element) throws EngidexNotInitalized, CloneNotSupportedException{
        if(Engidex.size() == 0){
            throw new EngidexNotInitalized();
        }

        List<Engimon> EngimonList = new ArrayList<Engimon>();
        for( Engimon e : Engidex.values()){
            for( Element el : e.getElement()){
                if(el == element){
                    EngimonList.add(e.cloneEngimon());
                }
            }
        }

        return EngimonList;
    }


    public static Engimon getEngimonByElement(Element e1, Element e2) throws EngidexNotInitalized{
        if(Engidex.size() == 0){
            throw new EngidexNotInitalized();
        }
        for( Engimon e : Engidex.values()){
            if(e.isOneElement()){
                continue;
            }
            List<Element> elements = e.getElement();
            if(elements.contains(e1) && elements.contains(e2) ){
                return e;
            }
        }

        // Case not found 
        return null;
    }
    
    // Get the species name
    public static List<String> getEngimonSpeciesByElement(Element e) throws EngidexNotInitalized, CloneNotSupportedException {
        return getEngimonByElement(e).stream().map( engimon -> engimon.getSpecies()).collect(Collectors.toList());
    }

    public static String getEngimonSpeciesByElement(Element e1, Element e2) throws EngidexNotInitalized {
        return getEngimonByElement(e1, e2).getSpecies();
    }

    

}
