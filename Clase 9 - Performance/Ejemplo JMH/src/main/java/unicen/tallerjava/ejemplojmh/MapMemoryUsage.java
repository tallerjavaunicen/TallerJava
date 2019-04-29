package unicen.tallerjava.ejemplojmh;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.BenchmarkParams;

public class MapMemoryUsage {
	@State(Scope.Thread)
	public static class BenchmarkState {
		public HashMap<Integer, Float> pvsw = new HashMap<Integer, Float>();

		@Setup
		public void setup(BenchmarkParams params) {
			for (int i = 0; i < 150000; i++) {
				int j = (int) (Math.random() * 1000000);
			}
		}
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void testIntegers(BenchmarkState state) {

	}
}
