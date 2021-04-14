package main.java.engimon;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import main.java.element.Element;
import main.java.engimon.species.*;

class EngidexNotInitalized extends Exception {
    static final long serialVersionUID = 1L; // Idk if this is nescessary 

    public EngidexNotInitalized(){
        super("Engidex has not been initialized");
    }
    public EngidexNotInitalized(String errorMessage){
        super(errorMessage);
    }
}

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

        Engidex.put("e1", e1);
        Engidex.put("e2", e2);
        Engidex.put("e3", e3);
        Engidex.put("e4", e4);
        Engidex.put("e5", e5);
        Engidex.put("e6", e6);
        Engidex.put("e7", e7);
        Engidex.put("e8", e8);
        Engidex.put("e9", e9);
        Engidex.put("e10", e10);
        Engidex.put("e11", e11);
        Engidex.put("e12", e12);
        Engidex.put("e13", e13);
        Engidex.put("e14", e14);
        Engidex.put("e15", e15);

    }

    // Basic Methods
    public static Engimon getEngimonBySpecies(String species) throws Exception{
        if(Engidex.size() == 0){
            throw new EngidexNotInitalized();
        }
        if(Engidex.get(species) == null){
            throw new Exception("Species doesn't exist");
        } else {
            return Engidex.get(species);
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
            List<Element> elements = e.getElement();
            if(elements.get(0) == e1 && elements.get(1) == e2 ){
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
