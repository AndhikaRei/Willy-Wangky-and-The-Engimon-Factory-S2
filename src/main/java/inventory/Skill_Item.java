package main.java.inventory;

import java.util.List;
import java.util.Objects;

import main.java.element.*;
import main.java.skill.*;

public class Skill_Item {
    /*** FIELDS ***/
    private Skill contSkill;    // Skill yang disimpan pada item skill
    private int amount;         // Jumlah item yang dimiliki

    /*** METHODS ***/
    /** Default Constructor **/
    public Skill_Item() {
        contSkill = new Skill();
        contSkill.levelReset();
        amount = 0;
    }

    /** User-Defined Constructor **/
    public Skill_Item(Skill sk) {
        contSkill = new Skill(sk);
        contSkill.levelReset();
        amount = 1;
    }

    public Skill_Item(Skill sk, int n) {
        contSkill = new Skill(sk);
        contSkill.levelReset();
        amount = n;
    }

    /** Copy Constructor **/
    public Skill_Item(Skill_Item other) {
        this.contSkill = new Skill(other.contSkill);
        contSkill.levelReset();
        this.amount = other.amount;
    }

    /** SERVICE **/
    /** Mempelajari sebuah skill yang compatible */
    public Skill learn(Element el) {
        if (contSkill.isElementCompatible(el)) {
            if (amount > 0) {
                amount--;
                return new Skill(contSkill);
            } else {
                return null;
                // throw InvalidNotEnoughItemAmount();
            }
        } else {    // Kasus elemen tidak sesuai
            return null;
            // throw InvalidElementNotCompatible();
        } 
    }

    /** Mempelajari sebuah skill yang compatible */
    public Skill learn(List<Element> listEl) {
        if (contSkill.isElementCompatible(listEl)) {
            if (amount > 0) {
                amount--;
                return new Skill(contSkill);
            } else {
                return null;
                // throw InvalidNotEnoughItemAmount();
            }
        } else {    // Kasus elemen tidak sesuai
            return null;
            // throw InvalidElementNotCompatible();
        } 
    }

    /** Menambah jumlah amount skill item sebanyak 1*/
    public void addAmount() {
        amount++;
    }

    /** Menambah jumlah amount skill item sebanyak 1*/
    public void addAmount(int n) {
        amount += n;
    }

    /** GETTER **/
    /** Mengembalikan skill yang disimpan pada skill item */
    public Skill getSkill() {
        return new Skill(contSkill);
    }

    /** Mengembalikan jumlah amount skill item */
    public int getAmount() {
        return amount;
    }

    /** PRINTER **/
    public void showItem() {
        contSkill.showSkill();
        System.out.println("Amounts      : " + amount);
    }

    public void showSimpleItem() {
        contSkill.showSimpleSkill();
        System.err.println("N     : " + amount);
    }

    /** Equals apakah skill yang disimpan sama */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Skill_Item)) {
            return false;
        }
        Skill_Item skill_Item = (Skill_Item) o;
        return Objects.equals(contSkill, skill_Item.contSkill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contSkill, amount);
    }
    

}
