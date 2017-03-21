package edu.isistan.matrix.mult;

import java.util.concurrent.Semaphore;

public class SemaphoreMultiplication implements IMultiplication {

    @Override
    public double[][] multiply(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];
        Semaphore c =new Semaphore(0);
        for(int i = 0; i < a.length; i++)
            (new Thread(new ExecuteComputation(a, b, result, i, c))).start();
        try {
            c.acquire(a.length);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    private class ExecuteComputation implements Runnable{

        private double[][] a;
        private double[][] b;
        private double[][] result;
        private int i;
        private Semaphore control;
        
        public ExecuteComputation(double[][] a, double[][] b, double[][] result, int i, Semaphore control) {
            super();
            this.a = a;
            this.b = b;
            this.result = result;
            this.i = i;
            this.control = control;
        }

        @Override
        public void run() {
            for(int j = 0; j < b[0].length; j++) {
                double val = 0.0d;
                for(int k = 0; k < b.length; k++)
                    val += a[i][k] * b[k][j];
                result[i][j] = val;
            }
            control.release();;
        }
        
    }
}
