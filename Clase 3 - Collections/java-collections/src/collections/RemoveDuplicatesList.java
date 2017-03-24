package collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicatesList {

	public static void main(String[] args) {
		
		List<String> words = new ArrayList<String>();
		words.add("hello");
		words.add("java");
		words.add("java"); // <- duplicado!
		words.add("world");
		words.add("programming");
		
//		Set<String> aux = new HashSet<String>(words);
//		List<String> words2 = new ArrayList<String>();
//		for(int i=0;i<words.size();i++)
//			if(aux.contains(words.get(i))){
//				words2.add(words.get(i));
//				aux.remove(words.get(i));
//			}
//		
//		words.clear();
//		words.addAll(words2);
		
		Set<String> aux = new LinkedHashSet<String>(words);
		words.clear();
		words.addAll(aux);
		
		System.out.println(words);
	}
	
}
