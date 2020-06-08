package edu.isistan.solutions;

import java.util.Arrays;

import edu.isistan.IProblemSolver;
import edu.isistan.ProblemGen;

public class Solutions {

	public static void main(String[] args) {
		
		ProblemGen problemGen = new ProblemGen();
		
		IProblemSolver naive = new SolutionSortSearch();
		
		for(int i=0;i<20;i++) {
			problemGen.genRandomProblem(10000000); 
//			System.out.println(Arrays.toString(problemGen.getData()));
			long start = System.currentTimeMillis(); //acá no está haciendo el warm up para empezar con el benchmarking!!
			System.out.println(" -- Pairs: "+naive.isSumIn(problemGen.getData(), (int)(Math.random() * 2 * Integer.MAX_VALUE + Integer.MIN_VALUE/2)).size());
			start = System.currentTimeMillis() - start;
			System.out.println(start);
		}
	}
}
