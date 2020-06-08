package edu.isistan.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import edu.isistan.IProblemSolver;

public class SolutionNaive2 implements IProblemSolver{

	public List<Pair> isSumIn(int[] data, int sum) {
		
		List<Pair> pairs = new ArrayList<>();
		
		IntStream.range(0,  data.length)
	    .forEach(i -> IntStream.range(i+1,  data.length)
	        .filter(j -> i != j && data[i] + data[j] == sum)
	        .forEach(j -> pairs.add(new Pair(data[i], data[j])))
	    		);

      
		return pairs;
	}

}