package main.java.engimon;

import java.util.Map;

import main.java.element.Element;

public class Engidex { 
    public Map<String, Engimon> Engidex;
     
    Engidex(){

        Engimon e1 = new CyroCrystallize("CyroCrystallize sp.");
        Engimon e2 = new ElectroCharged("ElectroCharged sp.");
        Engimon e3 = new Frozen("Frozen sp.");
        Engimon e4 = new HydroCrystallize("HydroCrystallize sp.");
        Engimon e5 = new Melt("Melt sp.");
        Engimon e6 = new PyroCrystallize("PyroCrystallize sp.");
        Engimon e7 = new Superconductor("Superconductor sp.");
        Engimon e8 = new Overload("Overload sp.");

        this.Engidex.put("e1", e1);
        this.Engidex.put("e2", e2);
        this.Engidex.put("e3", e3);
        this.Engidex.put("e4", e4);
        this.Engidex.put("e5", e5);
        this.Engidex.put("e6", e6);
        this.Engidex.put("e7", e7);
        this.Engidex.put("e8", e8);

    }

    // Basic Methods 
    public Map<String, Engimon> getEngidex(){
        return this.Engidex;
    }

    public Engimon getEngimonBySpecies(String species) throws Exception{
        if(this.Engidex.get(species) == null){
            throw new Exception("Species doesn't exist");
        } else {
            return this.Engidex.get(species);
        }
    }

    public Engimon getEngimonByElement(Element element) throws Exception{
        // For( Engimon e : this.Engidex.values()){
        //     System.out.println(e);
        // }
        return this.Engidex.get("Hi");
    }

    public Engimon getEngimonByElement(Element e1, Element e2) throws Exception{
        return this.Engidex.get("Hi");
    }
}
