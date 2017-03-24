package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchExample {

	public static void main(String[] args) {
		
		List<String> words = new ArrayList<String>();
		
		words.add("hello");
		words.add("world");
		words.add("java");
		words.add("programming");
		
		System.out.println(words);
		
		System.out.println(Collections.binarySearch(words, "world"));
		System.out.println(Collections.binarySearch(words, "java"));
		
		Collections.sort(words);
		System.out.println(words);
		System.out.println(Collections.binarySearch(words, "world"));
		System.out.println(Collections.binarySearch(words, "java"));
		
		Collections.sort(words,Collections.reverseOrder());
		System.out.println(words);
		System.out.println(Collections.binarySearch(words, "world"));
		System.out.println(Collections.binarySearch(words, "java"));
		
	}
	
}
