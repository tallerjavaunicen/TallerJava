package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MapTest {

	enum Key {Hello,World,Java} 
	
	public static void main(String[] args) {
		
//		Map<Key,Integer> map = new HashMap<MapTest.Key, Integer>();
//		Map<Key,Integer> map = new Hashtable<MapTest.Key, Integer>();
//		Map<Key,Integer> map = new LinkedHashMap<MapTest.Key, Integer>();
//		map.put(Key.World, 4);
//		map.put(Key.Java, 5);
//		map.computeIfAbsent(Key.Hello, s -> {  map.computeIfAbsent(Key.Hello, t -> 1); 
//        return 2; 
//		}); 
//		
//		System.out.println(map);
//		
//		map.put(Key.Hello, 3);
//		System.out.println(map);
		
		
		Map<String,Integer> map = new Hashtable<String,Integer>();
		map.computeIfAbsent("Hello", s -> {  map.computeIfAbsent("Hello", t -> 1);
		return 2;});
		System.out.println(map);
		
		
		List<String> list = new ArrayList<>();
		list.add("milk");
		list.add("bread");
		list.add("sausage");
		Stream<String> stream = list.stream();
		list.add("eggs, don't forget eggs!");
		stream.forEach(System.out::println);

		
	}
	
}
