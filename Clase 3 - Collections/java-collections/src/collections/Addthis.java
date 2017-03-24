package collections;

import java.util.ArrayList;
import java.util.List;

public class Addthis {

	public static void main(String[] args) {
		
		List things = new ArrayList();
		things.add("java");
		things.add(things);
		System.out.println(things);
		System.out.println(things.hashCode());
	}
	
}
