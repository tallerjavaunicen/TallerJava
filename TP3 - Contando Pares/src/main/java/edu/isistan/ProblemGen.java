package edu.isistan;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProblemGen {
	
	protected int target;
	protected int[] data;
	
	
	public void genRandomProblem(int size) {
		target = (int) (Math.random() * 2 * Integer.MAX_VALUE + Integer.MIN_VALUE);
		data = new int[size];
		for(int i = 0; i < size; i++) {
			data[i] = (int) (Math.random() * Integer.MAX_VALUE + Integer.MIN_VALUE/2);
			data[i] -= Math.signum(data[i]); 
		}
	}
	

	public boolean genIrresolProblem(int size) {
		return this.genIrresolProblem(size, 
				(int) (Math.random() * 2 * Integer.MAX_VALUE + Integer.MIN_VALUE));
	}

	
	public boolean genIrresolProblem(int size, int startTarget) {
		Set<Integer> sols = new HashSet<Integer>(size*size/2, 1f);
		data = new int[size];
		for(int i = 0; i < size; i++){
			int v = (int) (Math.random() * Integer.MAX_VALUE + Integer.MIN_VALUE/2);
			data[i] = v - (int)Math.signum(v);
			for (int j = 0; j < i; j++){
				sols.add(v+data[j]);
			}
		}
		target = startTarget;
		while(sols.contains(target)){
			target++;
			if (target == startTarget){
				return false;
			}
		}
		return true;
	}
	
	public int getTarget() {
		return target;
	}

	public int[] getData() {
		return data;
	}

	@Override
	public String toString() {
		return "ProblemGen [target=" + target + ", data=" + Arrays.toString(data) + "]";
	}
}
