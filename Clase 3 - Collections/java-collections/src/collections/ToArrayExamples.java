package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToArrayExamples {

	public static void main(String[] args) {

		List<String> words = new ArrayList<String>();

		words.add("hello");
		words.add("world");
		words.add("java");
		words.add("programming");
		
		String [] wordsArray = (String[]) words.toArray();
		System.out.println(Arrays.toString(wordsArray));
		
		String [] wordsArray2 = new String[0];
		words.toArray(wordsArray2);
		System.out.println(Arrays.toString(wordsArray2));
		
		String [] wordsArray3 = new String[0];
		wordsArray3 = words.toArray(wordsArray3);
		System.out.println(Arrays.toString(wordsArray3));
		
		String [] wordsArray4 = new String[words.size()+1];
		wordsArray4 = words.toArray(wordsArray4);
		System.out.println("Size: "+words.size()+" "+"Length: "+wordsArray4.length);
		System.out.println(Arrays.toString(wordsArray4));
		
		String [] wordsArray5 = new String[words.size()-1];
		wordsArray5 = words.toArray(wordsArray5);
		System.out.println(Arrays.toString(wordsArray5));
		
		String [] wordsArray6 = new String[0];
		wordsArray6 = words.toArray(wordsArray6);
		words.remove(0);
		System.out.println(words);
		System.out.println(Arrays.toString(wordsArray6));
		
	}
}
