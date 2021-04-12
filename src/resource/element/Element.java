package resource.element;

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

}
