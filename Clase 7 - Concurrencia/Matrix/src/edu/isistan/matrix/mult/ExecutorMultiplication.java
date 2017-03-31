package edu.isistan.matrix.mult;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorMultiplication implements IMultiplication {

    @Override
    public double[][] multiply(double[][] a, double[][] b) {
        double[][] result = new double[a.length][];
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        @SuppressWarnings("unchecked")
        Future<double[]>[] futures = (Future<double[]>[]) new Future<?>[a.length];
        for(int i = 0; i < a.length; i++)
            futures[i] = executor.submit(new RowComputation(a, b, i));
        for(int i = 0; i < a.length; i++)
            try {
                result[i] = futures[i].get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        executor.shutdown();
        return result;
    }

    private class RowComputation implements Callable<double[]>{

        private double[][] a;
        private double[][] b;
        private int i;
        
        public RowComputation(double[][] a, double[][] b, int i) {
            super();
            this.a = a;
            this.b = b;
            this.i = i;
        }

        @Override
        public double[] call(){
            double[] row = new double[b[0].length];
            for(int j = 0; j < b[0].length; j++) {
                double val = 0.0d;
                for(int k = 0; k < b.length; k++)
                    val += a[i][k] * b[k][j];
                row[j] = val;
            }
            return row;
        }
        
    }
}
