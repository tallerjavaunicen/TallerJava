package edu.isistan.mergesort;

import java.util.concurrent.ForkJoinPool;

public class MergeSortTest {

    public static void genData(int[] data) {
        for(int i=0; i < data.length; i++)
            data[i] = (int)(Math.random()*data.length);
    }
    
    public static void main(String[] args) {
        int times = 1000;
        int[] data = new int[100000];
        long start = System.currentTimeMillis();
        for(int i=0; i < times; i++) {
            genData(data);
            MergeSortUtils.mergeSort(data);
        }
        System.out.println("Total time: " + (System.currentTimeMillis()-start) + " ms.");
        
        start = System.currentTimeMillis();
        ForkJoinPool fj = new ForkJoinPool();
        for(int i=0; i < times; i++) {
            genData(data);
            fj.invoke(new MergeSortAction(data));
        }
        System.out.println("Total time: " + (System.currentTimeMillis()-start) + " ms.");
    }
}
