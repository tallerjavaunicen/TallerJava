package edu.isistan.forkjoin;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * The bug problem resolve as a RecursiveTask. Using the 
 * fork/join Java Framework
 * @author fanat
 *
 */
public class SolverRecusiveTask extends RecursiveTask<Bag> {

    /**
     * 
     */
    private static final long serialVersionUID = 128449989831042959L;
    private int start;
    private Item[] items;
    private Bag solution;
    private Bag b;
    
    public SolverRecusiveTask(Bag b, Item[] items, int start) {
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
        if (ended && (b.getCurrentValue() > this.solution.getCurrentValue()))
            this.solution = (Bag)b.clone();
    }

    @Override
    protected Bag compute() {
        //If the current deep of the test is more
        //than the 20% of the amount of items
        //keep the generation sequential.
        if(start >= items.length * 0.2) {
            this.solution = (Bag) this.b.clone();
            computeDirect(this.b, this.items, this.start);
            return this.solution;
        }
        //Otherwise
        this.solution = b;
        List<SolverRecusiveTask> tasks = new LinkedList<>();
        //generate new possible sequence to evaluate 
        for(int j = start; j < items.length; j++) {
            Item i = items[j];
            if (b.addItem(i)) {
                tasks.add(new SolverRecusiveTask((Bag)b.clone(), items, j + 1));
                b.remove(i);
            }
        }
        //Starts each evaluation
        for(SolverRecusiveTask t: invokeAll(tasks)) {
            Bag par = t.join();
            //Get the best combination
            if (par.getCurrentValue() > this.solution.getCurrentValue())
                this.solution = par;
        }
        //return it
        return this.solution;
    }

    
    public static void main(String[] args) {
        Item[] items = Item.generate(75, 0, 50, 0, 500,1023,1202);
        SolverRecusiveTask spb = new SolverRecusiveTask(new Bag(100), items, 0);
        ForkJoinPool pool = new ForkJoinPool();
        long time = System.currentTimeMillis();
        Bag b = pool.invoke(spb);
        time = System.currentTimeMillis() - time;
        System.out.println(b);
        System.out.println("Total time: "+ time +" ms.");
    }
}
