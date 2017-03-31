package edu.isistan.matrix.mult;

public class MonitorMultiplication implements IMultiplication {

    @Override
    public double[][] multiply(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];
        Control c = new Control(a.length);
        for(int i = 0; i < a.length; i++)
            (new Thread(new RowComputation(a, b, result, i, c))).start();
        c.waitToFinish();
        return result;
    }

    private class Control {
        private int cantFinished;
        private int expected;
        
        public Control(int expected) {
            super();
            this.cantFinished = 0;
            this.expected = expected;
        }
        
        public synchronized void finishOne(){
            this.cantFinished++;
            if (this.cantFinished == this.expected)
                this.notify();
        }
        
        public synchronized void waitToFinish() {
            while (this.cantFinished != this.expected) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    private class RowComputation implements Runnable{

        private double[][] a;
        private double[][] b;
        private double[][] result;
        private int i;
        private Control control;
        
        public RowComputation(double[][] a, double[][] b, double[][] result, int i, Control control) {
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
            control.finishOne();
        }
        
    }
}
