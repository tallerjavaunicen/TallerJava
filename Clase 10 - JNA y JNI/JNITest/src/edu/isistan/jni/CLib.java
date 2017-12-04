

package edu.isistan.jni;

public class CLib {
	static {
		System.loadLibrary("JNILib");
	}
	public native double dotMultiplication(double[] a, double[] b);
}
