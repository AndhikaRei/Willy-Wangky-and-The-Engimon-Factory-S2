package main.java.engimon;

import java.util.Map;

import main.java.element.Element;

public class Engidex { 
    public Map<String, Engimon> Engidex;
     
    Engidex(){

        Engimon e1 = new Pyro("Pyro sp.", 13519);
        Engimon e2 = new Hydro("Hydro sp.", 13519);
        Engimon e3 = new Electro("Electro sp.", 13519);
        Engimon e4 = new Geo("Geo sp.", 13519);
        Engimon e5 = new Cryo("Cryo sp.", 13519);
        Engimon e6 = new Vaporize("Vaporize sp.", 13519);
        Engimon e7 = new Overload("Overload sp.", 13519);
        Engimon e8 = new PyroCrystallize("PyroCrystallize sp.", 13519);
        Engimon e9 = new Melt("Melt sp.", 13519);
        Engimon e10 = new ElectroCharged("ElectroCharged sp.", 13519);
        Engimon e11 = new HydroCrystallize("HydroCrystallize sp.", 13519);
        Engimon e12 = new Frozen("Frozen sp.", 13519);
        Engimon e13 = new ElectroCrystallize("PyroCrystallize sp.", 13519);
        Engimon e14 = new Superconductor("Superconductor sp.", 13519);
        Engimon e15 = new CryoCrystallize("CyroCrystallize sp.", 13519);

        this.Engidex.put("Pyro", e1);
        this.Engidex.put("Hydro", e2);
        this.Engidex.put("Electro", e3);
        this.Engidex.put("Geo", e4);
        this.Engidex.put("Cryo", e5);
        this.Engidex.put("Vaporize", e6);
        this.Engidex.put("Overload", e7);
        this.Engidex.put("PyroCrystallize", e8);
        this.Engidex.put("Melt", e9);
        this.Engidex.put("ElectroCharged", e10);
        this.Engidex.put("HydroCrystallize", e11);
        this.Engidex.put("Frozen", e12);
        this.Engidex.put("ElectroCrystallize", e13);
        this.Engidex.put("Superconductor", e14);
        this.Engidex.put("CryoCrystallize", e15);

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
