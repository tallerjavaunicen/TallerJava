package edu.isistan.matrix;

import edu.isistan.matrix.mult.ExecutorMultiplication;
import edu.isistan.matrix.mult.MonitorMultiplication;
import edu.isistan.matrix.mult.SemaphoreMultiplication;
import edu.isistan.matrix.mult.SimpleMultiplication;

public class Main {

    public static void main(String[] args) {
        double[][] a = Utils.generateDenseSquareMatrix(2000);
        double[][] b = Utils.generateDenseSquareMatrix(2000);
        System.out.println(Utils.measureTime(a, b, new SimpleMultiplication()));
        System.out.println(Utils.measureTime(a, b, new MonitorMultiplication()));
        System.out.println(Utils.measureTime(a, b, new SemaphoreMultiplication()));
        System.out.println(Utils.measureTime(a, b, new ExecutorMultiplication()));
        System.out.println(Utils.verifyMultiplication(a, b, new MonitorMultiplication(), 3));
        System.out.println(Utils.verifyMultiplication(a, b, new SemaphoreMultiplication(), 3));
        System.out.println(Utils.verifyMultiplication(a, b, new ExecutorMultiplication(), 3));
    }

}
