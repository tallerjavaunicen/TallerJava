package unicen.tallerjava;

import java.io.IOException;
import java.util.Iterator;

import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntSet;

public class MapMemoryUsage {
	public static void main(String[] args) throws IOException {
		// HashMap<Integer, Float> map = new HashMap<>();
		// for (int i = 0; i < 5000000; i++) {
		// map.put(i, (float) Math.random() * 100);
		// }
		// System.in.read();
		// System.out.println(map.size());
		Int2FloatOpenHashMap intFloatMap = new Int2FloatOpenHashMap();
		for (int i = 0; i < 5000000; i++) {
			intFloatMap.put(i, (float) Math.random() * 100);
		}
//		Iterator<Integer> it = intFloatMap.keySet().iterator();

//		IntSet intSet = intFloatMap.keySet();
//		IntIterator it = intFloatMap.keySet().iterator();
		System.in.read();
		System.out.println(intFloatMap.size());
	}
}
