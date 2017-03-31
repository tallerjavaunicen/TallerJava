package edu.isistan.forkjoin;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The bug problem resolve as a RecursiveTask. Using the 
 * fork/join Java Framework
 * @author fanat
 *
 */
public class SolverRecusiveActionReadWriteLock extends RecursiveAction {

    /**
     * 
     */

    private static Bag SOLUTION;
    private static ReadWriteLock LOCK = new ReentrantReadWriteLock();
    
    private static final long serialVersionUID = 128449989831042959L;
    private int start;
    private Item[] items;
    private Bag b;
    
    public SolverRecusiveActionReadWriteLock(Bag b, Item[] items, int start) {
        super();
        this.b = b;
        this.items = items;
        this.start = start;
    }

    //This part of the algorithm is the sequential part, 
    //it is the same as the one found in SimpleBagProblem
    public void computeDirect(Bag b, Item[] items, int start) {
        boolean ended = true;
        for(int j = start; j < items.length; j++) {
            Item i = items[j];
            if (b.addItem(i)) {
                ended = false;
                computeDirect(b, items, j + 1);
                b.remove(i);
            }
        }
        if (ended){
            LOCK.readLock().lock();
            if (b.getCurrentValue() > SOLUTION.getCurrentValue()){
                LOCK.readLock().unlock();
                LOCK.writeLock().lock();
                if (b.getCurrentValue() > SOLUTION.getCurrentValue()){
                    SOLUTION = (Bag)b.clone();
                }
                LOCK.writeLock().unlock();
            } else {
                LOCK.readLock().unlock();
            }
        }
    }

    @Override
    protected void compute() {
        //If the current deep of the test is more
        //than the 20% of the amount of items
        //keep the generation sequential.
        if(start >= items.length * 0.2) {
            computeDirect(this.b, this.items, this.start);
            return;
        }
        //Otherwise
        List<SolverRecusiveActionReadWriteLock> tasks = new LinkedList<>();
        //generate new possible sequence to evaluate 
        for(int j = start; j < items.length; j++) {
            Item i = items[j];
            if (b.addItem(i)) {
                tasks.add(new SolverRecusiveActionReadWriteLock((Bag)b.clone(), items, j + 1));
                b.remove(i);
            }
        }
        //Starts each evaluation
        invokeAll(tasks);
    }

    
    public static void main(String[] args) {
        Item[] items = Item.generate(75, 0, 50, 0, 500,1023,1202);
        SOLUTION = new Bag(100);
        SolverRecusiveActionReadWriteLock spb = new SolverRecusiveActionReadWriteLock(new Bag(100), items, 0);
        ForkJoinPool pool = new ForkJoinPool();
        long time = System.currentTimeMillis();
        pool.invoke(spb);
        time = System.currentTimeMillis() - time;
        System.out.println(SOLUTION);
        System.out.println("Total time: "+ time +" ms.");
    }
}
