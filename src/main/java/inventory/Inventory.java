package main.java.inventory;
import main.java.engimon.Engimon;
import main.java.exception.ItemNotEnoughAmountException;
import main.java.exception.SkillElementNotCompatibleException;

import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

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
        this.sortEngimons();
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
        sortItems();
    }
    public void useItem(int i, E engimon) throws ItemNotEnoughAmountException, SkillElementNotCompatibleException {
        this.ListItem.get(i).learn(engimon.getElement());
        if(this.ListItem.get(i).getAmount() < 1){
            this.ListItem.remove(i);
        }
        sortItems();
    }

    public void KillEngimon(E engimon){
        for(int i = 0;i < this.ListEngimon.size();i++){
            if(this.ListEngimon.get(i).equals(engimon)){
                this.ListEngimon.get(i).decrementLive();
                if(this.ListEngimon.get(i).getLives() == 0) {
                    this.ListEngimon.remove(i);
                }
                return;
            }
        }
        this.sortEngimons();
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

    public void sortEngimons(){
        this.ListEngimon = this.ListEngimon.stream().sorted().collect(Collectors.toList());
    }
    public void sortItems(){
        this.ListItem = this.ListItem.stream().sorted().collect(Collectors.toList());
    }
}
