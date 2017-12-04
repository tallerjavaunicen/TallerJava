package edu.isistan;

import com.sun.jna.Library;

public interface CLib extends Library{
	double dotMultiplication(double[] a, double[] b, int len);
}
