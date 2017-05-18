package unice.tallerjava.ejemplojmh;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import unicen.tallerjava.PrimitiveVsWrapper;

public class JMHMaximumInt {

	@State(Scope.Thread)
	public static class BenchmarkState {
		public PrimitiveVsWrapper pvsw = new PrimitiveVsWrapper();
		public Integer[] integers = new Integer[100000];
		public int[] ints = new int[100000];

		@Setup
		public void setup(BenchmarkParams params) {
			for (int i = 0; i < ints.length; i++) {
				int j = (int) (Math.random() * 1000000);
				integers[i] = j;
				ints[i] = j;
			}
		}
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void testIntegers(BenchmarkState state) {
		state.pvsw.getMaximum(state.integers);
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void testInts(BenchmarkState state) {
		state.pvsw.getMaximumInt(state.ints);
	}

	public static void main(String[] args) throws RunnerException, IOException {
		Options opt = new OptionsBuilder()
                .include(JMHMaximumInt.class.getSimpleName())
                .warmupIterations(5)// number of times the warmup iteration should take place
                .measurementIterations(5)//number of times the actual iteration should take place
                .forks(3)
                .build();


        new Runner(opt).run(); 
	}
}
