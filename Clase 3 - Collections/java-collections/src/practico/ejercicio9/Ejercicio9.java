package practico.ejercicio9;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio9 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(10);

		System.out.print(list.size());
		list.remove(new Integer(10));

		System.out.print(list.size());
	}
}

