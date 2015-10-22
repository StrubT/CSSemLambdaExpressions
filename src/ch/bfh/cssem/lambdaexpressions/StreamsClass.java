package ch.bfh.cssem.lambdaexpressions;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsClass {

	public static void main(String args[]) {

		Collection<String> names = Arrays.asList("Hans", "Peter", "Erich", "Heinz", "Heidi", "Marianne", "Hennrich");
		Collection<String> filtered = names.stream().filter(l -> l.startsWith("H")).map(String::toUpperCase).collect(Collectors.toList());

		filtered.forEach(System.out::println);
	}
}


