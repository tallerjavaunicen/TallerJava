package edu.isistan;

import com.sun.jna.Native;

public class Main {

	public static void main(String[] args) {
		test1();
		test2();
	}

	public static void test1() {
		double[] a = new double[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		System.out.println(dot(a, a));
		long time = System.currentTimeMillis();
		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < Integer.MAX_VALUE; j++)
				dot(a, a);
		}
		System.out.println(System.currentTimeMillis()-time);
	}
	
	public static double dot(double[] a, double[] b) {
		double d = 0;
		for(int i=0; i< a.length;i++)
			d += a[i] * b[i];
		return d;
	}
	
	public static void test2() {
		double[] a = new double[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		CLib lib = (CLib) Native.loadLibrary("JNALib", CLib.class);
		System.out.println(lib.dotMultiplication(a, a, a.length));
		long time = System.currentTimeMillis();
		for(int j = 0; j < 1000000; j++)
			lib.dotMultiplication(a, a, a.length);
		System.out.println(System.currentTimeMillis()-time);
	}
}
