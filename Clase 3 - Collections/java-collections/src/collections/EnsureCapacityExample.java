package collections;

import java.util.ArrayList;
import java.util.List;

public class EnsureCapacityExample {

	public static void main(String[] args) {
		
		long time = System.currentTimeMillis();
	
		List<String> numbs = new ArrayList<String>();
		for(int i=0;i<5000000;i++)
			numbs.add(Integer.toString(i));
		
		time = System.currentTimeMillis()-time;
		System.out.println(time);
		
		numbs.clear();
		time = System.currentTimeMillis();
		((ArrayList)numbs).ensureCapacity(500000);
		for(int i=0;i<5000000;i++)
			numbs.add(Integer.toString(i));
		time = System.currentTimeMillis()-time;
		System.out.println(time);
		
		time = System.currentTimeMillis();
		List<String> numbs2 = new ArrayList<String>();
		numbs2.addAll(numbs);
		time = System.currentTimeMillis()-time;
		System.out.println(time);
		
		numbs2.clear();
		time = System.currentTimeMillis();
		((ArrayList)numbs2).ensureCapacity(numbs.size());
		for(int i=0;i<numbs.size();i++)
			numbs2.add(numbs.get(i));
		time = System.currentTimeMillis()-time;
		System.out.println(time);
		
	}
	
}
