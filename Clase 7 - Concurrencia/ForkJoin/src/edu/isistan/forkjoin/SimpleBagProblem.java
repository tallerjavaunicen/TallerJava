package edu.isistan.forkjoin;
/**
 * Soluciona el problema de la mochila de forma secuencial.
 * @author Juan Manuel Rodriguez
 *
 */
public class SimpleBagProblem {

    private Bag solution;
    
    public SimpleBagProblem(Bag b) {
        super();
        this.solution = b;
    }

    public static void main(String[] args) {
        Bag b = new Bag(100);
        Item[] items = Item.generate(75, 0, 50, 0, 500,1023,1202);//73
        SimpleBagProblem spb = new SimpleBagProblem(new Bag(100));
        long time = System.currentTimeMillis();
        spb.solve(b, items, 0);
        time = System.currentTimeMillis() - time;
        System.out.println(spb);
        System.out.println("Total time: "+ time +" ms.");
    }

    public void solve(Bag b, Item[] items, int start) {
        boolean ended = true;
        //For each remaining item
        for(int j = start; j < items.length; j++) {
            Item i = items[j];
            //Test what happens if the item is or not in the bag.
            if (b.addItem(i)) {
                ended = false;
                //The rest of the items are tested recursively
                solve(b, items, j + 1);
                b.remove(i);
            }
        }
        //If no item could be added to the bug because
        //there is neither space or another item,
        //the current bag configuration is a potential solution
        if (ended && (b.getCurrentValue() > this.solution.getCurrentValue()))
            this.solution = (Bag)b.clone();

    }

    @Override
    public String toString() {
        return "SimpleBagProblem [solution=" + solution + "]";
    }
    
    
}
