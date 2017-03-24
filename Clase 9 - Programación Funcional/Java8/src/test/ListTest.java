package test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class ListTest {

	public static void main(String[] args) {
//		System.out.println(Stream.of(-3, -2, -1, 0, 1, 2, 3).max(Math::max).get());
		
//		List<String> kitties = Arrays.asList("Soft", "Warm", "Purr");
//		Comparator<String> kittiesComparator= Comparator.nullsLast(Comparator.naturalOrder());
//		System.out.println(Collections.max(kitties, kittiesComparator));
//		System.out.println(kitties.stream().collect(Collectors.maxBy(kittiesComparator)).get());
//		System.out.println(kitties.stream().max(kittiesComparator).get());
		
//		List<String> kitties = Arrays.asList("Soft", null, "Purr");
//		Comparator<String> kittiesComparator= Comparator.nullsLast(Comparator.naturalOrder());
//		System.out.println(Collections.max(kitties, kittiesComparator));
//		System.out.println(kitties.stream().collect(Collectors.maxBy(kittiesComparator)));
//		System.out.println(kitties.stream().collect(Collectors.maxBy(kittiesComparator)).get());
//		System.out.println(kitties.stream().max(kittiesComparator).get());
		
		
//		System.out.println(Optional.of("rtfm").orElseGet(null));
//		System.out.println(Optional.empty().map(null).orElse("rtfm"));

//		List<String> words = Arrays.asList("Hello","Java","World");
//		Stream<String> s = words.stream();
//		s.forEach(System.out::println);
//		s.forEach(System.out::println);

//		List<Integer> numbers = Arrays.asList(1, 1, 2, 2, 3, 3, 4);
//		numbers.stream()
//		.filter(i -> i % 2 == 0)
//		.limit(4)
//		.forEach(System.out::println);
		
//		Set<Integer> numbers = new HashSet<Integer>(Arrays.asList(1, 1, 2, 2, 3, 3, 4));
//		numbers.stream()
//		.filter(i -> i % 2 == 0)
//		.skip(4)
//		.forEach(System.out::println);
		
		List<String> words = Arrays.asList("Hello","World");
		List<String[]> chars = words.stream()
		.map(word -> word.split(""))
		.distinct()
		.collect(toList());

		for(String[] c : chars)
			System.out.println(Arrays.toString(c));
		
		String[] arrayOfWords = {"Hello", "World"};
		List<Stream<String>> streamOfwords = Arrays.stream(arrayOfWords)
		    .map(word -> word.split(""))
		    .map(Arrays::stream)
		    .distinct()
		    .collect(toList());

		for(Stream<String> c : streamOfwords)
			c.forEach(System.out::println);
		
		List<String> uniqueCharacteres = words.stream()
			    .map(w -> w.split(""))
			    .flatMap(Arrays::stream)
			    .distinct()
			    .collect(Collectors.toList());
		System.out.println(uniqueCharacteres);
	}
	
}
