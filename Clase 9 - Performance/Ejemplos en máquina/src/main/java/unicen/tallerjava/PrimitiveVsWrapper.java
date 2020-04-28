package unicen.tallerjava;

import java.io.IOException;

public class PrimitiveVsWrapper {

	public Integer getMaximum(Integer[] list) {
		int[] convertidos = new int[list.length];
		for (int i = 0; i < list.length; i++) {
			convertidos[i] = list[i];
		}
		Integer max = Integer.MIN_VALUE;
		for (int i = 0; i < list.length; i++) {
			if (list[i] > max)
				max = list[i];
		}
		return max;
	}

	public int getMaximumInt(int[] list) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < list.length; i++) {
			if (list[i] > max)
				max = list[i];
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		PrimitiveVsWrapper prim = new PrimitiveVsWrapper();

		Integer[] integers = new Integer[1000000];
		int[] ints = new int[1000000];

		for (int i = 0; i < ints.length; i++) {
			int j = (int) (Math.random() * 10000);
			integers[i] = j;
			ints[i] = j;
		}

		// System.in.read();

		for (int i = 0; i < 100000000; i++) {
			prim.getMaximum(integers);
			prim.getMaximumInt(ints);
		}

		// long init = System.nanoTime();
		// prim.getMaximum(integers);
		// System.out.println(System.nanoTime() - init + " ns");

		long init = System.nanoTime();
		prim.getMaximumInt(ints);
		System.out.println(System.nanoTime() - init + " ns");
	}
}
