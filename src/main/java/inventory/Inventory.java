package main.java.inventory;
import main.java.engimon.Engimon;

import java.util.*;

public class Inventory<E extends Engimon,I extends Skill_Item> {
    private List<I> ListItem;
    private List<E> ListEngimon;
    public Inventory(){
        this.ListItem = new ArrayList<I>();
        this.ListEngimon = new ArrayList<E>();
    }

    /** modify data**/
    public void addEngimon(E Engimon){
        this.ListEngimon.add(Engimon);
    }
    public void addItem(I Item){
        Boolean found = false;
//        this.ListItem
//                .stream()
//                .filter( i -> i.equals(Item))
//                .map(Skill_Item::addAmount);
//                //.ifPresent(found = true);

        for(int i = 0; i< this.ListItem.size();i++){
            if(this.ListItem.get(i).equals(Item)){
                this.ListItem.get(i).addAmount(Item.getAmount());
                found = true;
            }
        }
        if(found == false){
            ListItem.add(Item);
        }
    }
    public void useItem(int i, E engimon){
        this.ListItem.get(i).learn(engimon.getElement());
        if(this.ListItem.get(i).getAmount() < 1){
            this.ListItem.remove(i);
        }
    }

    public void KillEngimon(E engimon){
        for(int i = 0;i < this.ListEngimon.size();i++){
            if(this.ListEngimon.get(i).equals(engimon)){
                this.ListEngimon.remove(i);
                return;
            }
        }
        //throw something
    }
    /** getter**/
    public List<I> getItems(){
        return this.ListItem;
    }
    public List<E>  getEngimons(){
        return this.ListEngimon;
    }
    public I getItem(Integer i){
        return this.ListItem.get(i);
    }
    public E getEngimon(Integer i){
        return this.ListEngimon.get(i);
    }

    /** printer**/
    public void printItems(){
        System.out.println("Items : ");
        for(int i = 0;i < this.ListItem.size();i++){
            System.out.print(i);
            System.out.print("] ");
            this.ListItem.get(i).showSimpleItem();
        }
    }
    public void printEngimons(){
        System.out.println("Engimon : ");
        for(int i = 0;i < this.ListEngimon.size();i++){
            System.out.print(i);
            System.out.print("] ");
            this.ListEngimon.get(i).printEngimon();
        }
    }
    public void printInventory(){
        printEngimons();
        printItems();
    }
}