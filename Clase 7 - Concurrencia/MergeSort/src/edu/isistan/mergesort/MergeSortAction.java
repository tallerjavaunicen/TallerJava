package edu.isistan.mergesort;

import java.util.concurrent.RecursiveAction;

public class MergeSortAction extends RecursiveAction {

    /**
     * 
     */
    private static final long serialVersionUID = 7873623049487344984L;

    private int[] array;
    private int i;
    private int max;
    private int threasholds;
    
    public MergeSortAction(int[] array) {
        this(array, 0, array.length-1, 0);
    }


    public MergeSortAction(int[] array, int threasholds) {
        this(array, 0, array.length-1, threasholds);
    }


    public MergeSortAction(int[] array, int i, int max, int threasholds) {
        super();
        this.array = array;
        this.i = i;
        this.max = max;
        this.threasholds = threasholds;
    }

    @Override
    protected void compute() {
        if(threasholds >= (max-i)) {
            MergeSortUtils.mergeSort(array, i, max);
            return;
        }
        int mid = (max + i) / 2;
        invokeAll(new MergeSortAction(array, i, mid, threasholds), 
                new MergeSortAction(array, mid + 1, max, threasholds));
        MergeSortUtils.merge(array, i, mid, max);
    }

}
