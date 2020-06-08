package edu.isistan.solutions;

import java.util.ArrayList;
import java.util.List;

import edu.isistan.IProblemSolver;

public class SolutionNaive implements IProblemSolver{

	public List<Pair> isSumIn(int[] data, int sum) {
		
		List<Pair> pairs = new ArrayList<>();
		
        for (int i = 0; i < data.length; i++) 
            for (int j = i + 1; j < data.length; j++) 
                if ((data[i] + data[j]) == sum) 
                    pairs.add(new Pair(data[i],data[j])); 
        
		return pairs;
	}

}
