package edu.isistan.forkjoin;

import java.util.HashSet;
import java.util.Set;

public class Bag implements Cloneable{
    
    private int maxWeight;
    private Set<Item> items;
    private int currentWeight;
    private int currentValue;
    
    private Bag() {
        super();
    }

    public Bag(int maxWeight) {
        super();
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
        this.currentValue = 0;
        this.items = new HashSet<>();
    }
    
    public boolean addItem(Item i){
        if ((this.currentWeight+i.getWeight()<=this.maxWeight) &&
                (!this.items.contains(i))){
            this.currentWeight += i.getWeight();
            this.currentValue += i.getValue();
            this.items.add(i);
            return true;
        }
        return false;
    }
    
    public boolean remove(Item i){
        if (this.items.remove(i)){
            this.currentWeight -= i.getWeight();
            this.currentValue -= i.getValue();
            return true;
        }
        return false;
    }
    
    public int getCurrentWeight() {
        return this.currentWeight;
    }
    
    public int getCurrentValue() {
        return this.currentValue;
    }

    @Override
    public Object clone() {
        Bag b = new Bag();
        b.currentWeight = this.currentWeight;
        b.items = new HashSet<>(this.items);
        b.maxWeight = this.maxWeight;
        b.currentValue = this.currentValue;
        return b;
    }

    @Override
    public String toString() {
        return "Bag [maxWeight=" + maxWeight + ", items=" + items + ", currentWeight=" + currentWeight
                + ", currentValue=" + currentValue + "]";
    }
    
    
}
