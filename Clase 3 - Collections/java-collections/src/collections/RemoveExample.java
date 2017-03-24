package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RemoveExample {
	
	public static void main(String[] args) {
		
		List<String> words = new ArrayList<String>();
		words.add("hello");
		words.add("world");
		words.add("java");
		words.add("programming");
		words.add("java");
		words.add(null);
		
		String toRemove = "java";
		
		boolean removed = true;
		while(removed){
			removed = words.remove(toRemove);
		}
		
		List<String> toRemoveC = new ArrayList<String>();
		toRemoveC.add(toRemove);
		words.removeAll(toRemoveC);
		
		words.removeAll(Collections.singleton(toRemove));
		
		System.out.println(words);
		
	}
	
}
