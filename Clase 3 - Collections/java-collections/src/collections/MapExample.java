package collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapExample {

	public static Map<String,Integer> createMap(String line,Map<String,Integer> toFill){
		
		String [] split = line.split(" ");
		
		for (String a : split) {
	            Integer freq = toFill.get(a);
	            toFill.put(a, (freq == null) ? 1 : freq + 1);
	        }
		 return toFill;
	}
	
	public static void main(String[] args) {
        
		String line = "He did not know or care whether they were wizards or Muggles, friends or foes";
		
		Map<String,Integer> hashMap = createMap(line,new HashMap<String,Integer>());
		System.out.println(hashMap);
		
		Map<String,Integer> treeMap = createMap(line,new TreeMap<String,Integer>());
		System.out.println(treeMap);
		
		Map<String,Integer> linkedHashMap = createMap(line,new LinkedHashMap<String,Integer>());
		System.out.println(linkedHashMap);

	}
	
}
