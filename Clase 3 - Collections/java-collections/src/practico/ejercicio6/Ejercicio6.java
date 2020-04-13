package practico.ejercicio6;

import java.util.ArrayDeque;

public class Ejercicio6{

	public static void main(String[] args){

		ArrayDeque<String> adq = new ArrayDeque<String>();

		adq.add("A");
		adq.push("B");
		adq.addFirst("C");
		adq.offer("D");

		System.out.print(adq.peek() + " " + adq.pop() + " " + adq.poll());
	}
}
