package unicen.tallerjava;
public class PrimitiveVsWrapper {

	public Integer getMaximum(Integer[] list) {
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

	public static void main(String[] args) {
		PrimitiveVsWrapper prim = new PrimitiveVsWrapper();

		Integer[] integers = new Integer[1000];
		int[] ints = new int[1000];

		for (int i = 0; i < ints.length; i++) {
			int j = (int) (Math.random() * 10000);
			integers[i] = j;
			ints[i] = j;
		}

		for (int i = 0; i < 100000; i++) {
			prim.getMaximum(integers);
			prim.getMaximumInt(ints);
		}

		long init = System.nanoTime();
		prim.getMaximum(integers);
		System.out.println(System.nanoTime() - init + " ns");

		// init = System.nanoTime();
		// prim.getMaximumInt(ints);
		// System.out.println(System.nanoTime() - init + " ns");
	}
}
