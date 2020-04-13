package practico.ejercicio5;

import java.util.LinkedList;

public class Ejercicio5 {
	public static void main(String[] args) {

		LinkedList<String> ll = new LinkedList<String>();
		ll.add("C");
		ll.push("B");	
		ll.addFirst("A");
		ll.offer("D");
		System.out.print(ll.remove() + " ");
		System.out.print(ll.poll());
	}
}

