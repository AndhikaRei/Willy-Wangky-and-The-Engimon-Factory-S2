package resource.element.test;

import resource.element.*;

public class Test_Element {
    public static void main(String[] args) {
        System.out.println("Test Element");
        Element el1 = Element.Electric;
        Element el2 = Element.Water;
        
        double adv = Element.advantage(el1, el2);
        System.out.println(adv);

        // if (Element.Fire == Element.Fire) {
        //     System.out.println("Yess");
        // }
        

    }
}
