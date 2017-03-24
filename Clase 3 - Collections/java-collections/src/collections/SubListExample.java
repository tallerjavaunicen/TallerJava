package collections;

import java.util.ArrayList;
import java.util.List;

public class SubListExample {

	public static void main(String[] args) {
		
		List<String> words = new ArrayList<String>();
		words.add("hello");
		words.add("world");
		words.add("java");
		words.add("programming");
		words.add("java");
		words.add(null);
		
		List<String> subList1 = words.subList(0, 2);
		
		subList1.remove(0);
		
		System.out.println(subList1);
		System.out.println(words);
		
		words.remove(1);
		System.out.println(words);
		System.out.println(subList1);

		
	}
	
}
