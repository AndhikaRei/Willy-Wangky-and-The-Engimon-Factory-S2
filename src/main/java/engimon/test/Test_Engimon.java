package main.java.engimon.test;

import main.java.engimon.*;

/** langkah compile dan run:
 * javac main/java/engimon/test/Test_Engimon.java
 * java main.java.engimon.test.Test_Engimon
 */

public class Test_Engimon {
    public static void main(String[] args) {
        Parent par = new Parent("papa", "laki-laki", "mama", "perempuan");
        Engimon awewe = new Melt("andru", 135, par);
        awewe.printEngimon();
    }

}
