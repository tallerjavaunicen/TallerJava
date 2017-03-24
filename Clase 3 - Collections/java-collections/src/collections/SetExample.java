package collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetExample {

	public static void main(String[] args) {
		
		List<String> a = new ArrayList<String>();
		a.add("a");
		
		Set<List<String>> b = new HashSet<List<String>>();
		b.add(a);
		
		List<List<String>> c = new ArrayList<List<String>>();
		c.add(a);
		
		System.out.println(b);
		System.out.println(c);
		
		a.add("b");
		
		System.out.println(b);
		System.out.println(c);
		System.out.println(b.contains(a));
		System.out.println(c.contains(a));

	}
	
}
