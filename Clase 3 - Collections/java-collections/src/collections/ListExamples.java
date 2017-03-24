package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListExamples {

	public static void main(String[] args) {
		
		List<String> nombres = new ArrayList<String>();

//		System.out.println(nombres.get(0));
//		nombres.add(0,"Andrea");
//		System.out.println(nombres);

		List<Integer> numeros= new ArrayList<Integer>();

//		numeros.add(1);
//		numeros.add(2);
//		numeros.add(3);
//		numeros.add(4);
//		
//		System.out.println(numeros);
//
//		numeros.remove(3);
//		System.out.println(numeros);
//
//		numeros.remove(new Integer(3));
//		System.out.println(numeros);
		
//		List<String> hijos = new ArrayList<String>();
//		hijos.add(null);
//
//		System.out.println(hijos.get(0) == null);
//		
//	    List<Integer> list = new ArrayList<Integer>();	    
//	    Integer[] arr = {2,10,3};
//	    list = Arrays.asList(arr);
//	    list.set(0, 3);
//	    System.out.println(list);
//	    list.add(1);
//	    System.out.println(list);
	
//	    List<Integer> list = new ArrayList<Integer>();
//	    
//	    list.add(10);
//	    list.add(10);
//	    
//	    System.out.println(list.size());
//	    
//	    list.remove(new Integer(10));
//	    
//	    System.out.println(list.size());

	    
		LinkedList<String> linkedL = new LinkedList<String>();

	    linkedL.add("C"); 
	    System.out.println(linkedL);
	    linkedL.push("B");
	    System.out.println(linkedL);
	    linkedL.addFirst("A");
	    System.out.println(linkedL);
	    linkedL.offer("D");
	    System.out.println(linkedL);
	    
	    System.out.println(linkedL.remove());
	    System.out.println(linkedL.peek());
	    System.out.print(linkedL.poll());
	    
		
	}
	
}
