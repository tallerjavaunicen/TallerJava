package edu.isistan;

import java.util.List;

public interface IProblemSolver {

	/**
	 * Look in the data two integers that sums to target.
	 * @param data
	 * @param target
	 * @return
	 */
	public List<Pair> isSumIn(int[] data, int sum);
	
	public static class Pair{
		private int i;
		private int j;
		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		public int getI() {
			return i;
		}
		public int getJ() {
			return j;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			result = prime * result + j;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Pair [i=" + i + ", j=" + j + "]";
		}
		
	}
}
