package main.java.element;

import javafx.scene.image.Image;

import java.util.List;

public enum Element {
    /* DAFTAR ELEMENT */
    Fire(0),
    Water(1),
    Electric(2),
    Ground(3),
    Ice(4);


    /* ELEMENT ADVANTAGE */
    private static final double[][] ElementAdvantage =
    {{1,0,1,0.5,2},
    {2,1,0,1,1},
    {1,2,1,0,1.5},
    {1.5,1,2,1,0},
    {0,1,0.5,2,1}};

    /* id Element*/
    private final int id;

    /**
     * Private constructor untuk element */ 
    Element(int id) {
        this.id = id;
    }

    /**
     * Mengembalikan id suatu element */ 
    public static int getId(Element el) {
        return el.id;
    }

    /**
     * Mengembalikan nama suatu element */
    public static String getName(Element el) {
        return el.name();
    }

    /**
     * Mengembalikan Element Advantage el1 terhadap el2 */
    public static double advantage(Element el1, Element el2) {
        return ElementAdvantage[el1.id][el2.id];
    }

    /**
     * Mengembalikan Element Advantage el1 terhadap list el2 */
    public static double advantage(Element el1, List<Element> list_el2) {
        double elAdv = 0;
        double curAdv;
        for (Element el2: list_el2) {
            curAdv = advantage(el1,el2);
            if (elAdv < curAdv) {
                elAdv = curAdv;
            }
        }
        return elAdv;
    }

    /**
     * Mengembalikan Element Advantage list el1 terhadap list el2 */
    public static double advantage(List<Element> list_el1, List<Element> list_el2) {
        double elAdv = 0;
        double curAdv;
        for (Element el1: list_el1) {
            for (Element el2: list_el2) {
                curAdv = advantage(el1,el2);
                if (elAdv < curAdv) {
                    elAdv = curAdv;
                }
            }
        }
        return elAdv;
    }
    public static Image getSpriteEl(Element el1, Double rw, Double rh){
        if (el1.equals(Element.Ice)){
            return new Image("./main/resources/el_ice.png",rw,rh,false,false);
        } else if(el1.equals(Element.Fire)){
            return new Image("./main/resources/el_fire.png",rw,rh,false,false);
        } else if(el1.equals(Element.Water)){
            return new Image("./main/resources/el_water.png",rw,rh,false,false);
        } else if(el1.equals(Element.Ground)){
            return new Image("./main/resources/el_earth.png",rw,rh,false,false);
        } else {
            return new Image("./main/resources/el_electric.png",rw,rh,false,false);
        }
    }

    public static int toInt(List<Element> in){
        int out = 0;
        for(int i = 0;i<in.size();i++){
            int temp = Element.getId(in.get(in.size()-i-1));
            if(temp == 0){
                temp = 5;
            }
            out += temp * 10 ^(in.size()-i-1);
        }
        return out;
    }
}
