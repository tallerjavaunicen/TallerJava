package edu.isistan.matrix.mult;

public class SimpleMultiplication implements IMultiplication {

    @Override
    public double[][] multiply(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];
        for(int i = 0; i < a.length; i++)
            for(int j = 0; j < b[0].length; j++) {
                double val = 0.0d;
                for(int k = 0; k < b.length; k++)
                    val += a[i][k] * b[k][j];
                result[i][j] = val;
            }
        return result;
    }

}
