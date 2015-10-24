package ch.bfh.cssem.lambdaexpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CollectionsClass {

	public static void main(String[] args) {

		Collection<Integer> numbers = new ArrayList<>(Arrays.asList(1, 3, 52, 47, 8, 2, 15, 38, 15, 7, 42, 31, 43, 5));
		System.out.println(String.join(", ", numbers.stream().map(Object::toString).collect(Collectors.toList())));

		numbers.removeIf(n -> n > 20);
		System.out.println(String.join(", ", numbers.stream().map(Object::toString).collect(Collectors.toList())));

		Iterable<String> students = Arrays.asList("strut1", "touwm1");
		for (String student : students)
			System.out.printf("By for loop: %s%n", student);

		students.forEach(n -> System.out.printf("By lambda expression: %s%n", n));

		int pageSize = 3;
		int pageIndex = 1;
		Collection<String> names = Arrays.asList("Heinrich", "Joschua", "Hans", "Peter", "Hella", "Marc", "Hagrid", "Erich", "Heinz", "Thomas", "Heidi", "Marianne", "Heinrich", "Janick", "Heinz", "Reto", "Hermione");
		Collection<String> namesH = names.stream()
																		 .parallel()
																		 .distinct()
																		 .map(String::toUpperCase)
																		 .filter(l -> l.startsWith("H"))
																		 .sorted()
																		 .skip(pageIndex * pageSize).limit(pageSize)
																		 .collect(Collectors.toList());

		System.out.println(String.join(", ", names));
		System.out.println(String.join(", ", namesH));
	}
}
