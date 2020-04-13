package practico.ejercicio8;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio8 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();

		list.add(10);
		list.add(10);

		System.out.print(list.size());

		list.remove(10);

		System.out.print(list.size());
	}
}
