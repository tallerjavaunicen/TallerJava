package edu.isistan.forkjoin;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Item {

    private static final AtomicInteger NEXT_ID = new AtomicInteger(0);
    
    public static Item[] generate(int cant, int minW, int maxW, int minV, int maxV, long seedv, long seedw) {
        Random rv = new Random(seedv);
        Random rw = new Random(seedw);
        Item[] res = new Item[cant];
        for(int i = 0; i < cant; i++)
            res[i] = new Item(rv.nextInt(maxV - minV) + minV , 
                    rw.nextInt(maxW - minW) + minW);
        return res;
    }
    
    public static Item[] generate(int cant, int minW, int maxW, int minV, int maxV) {
        Random rv = new Random();
        Random rw = new Random();
        Item[] res = new Item[cant];
        for(int i = 0; i < cant; i++)
            res[i] = new Item(rv.nextInt(maxV - minV) + minV , 
                    rw.nextInt(maxW - minW) + minW);
        return res;
    }
    
    private int id;
    private int value;
    private int weight;
    
    public Item(int value, int weight) {
        super();
        this.id = NEXT_ID.getAndIncrement();
        this.value = value;
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", value=" + value + ", weight=" + weight + "]";
    }
    
    
}
