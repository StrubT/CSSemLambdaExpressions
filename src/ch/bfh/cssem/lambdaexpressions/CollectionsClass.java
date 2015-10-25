package ch.bfh.cssem.lambdaexpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class with tests for the {@link Collection} framework.
 *
 * @author strut1 &amp; touwm1
 */
public class CollectionsClass {

	/**
	 * Main method / entry point to the application.
	 * Runs the tests.
	 *
	 * @param args command-line arguments (none used)
	 */
	public static void main(String... args) {

		List<Double> pointsToMarks = Arrays.asList(6.5, 9.75, 8.5, 3.0, 8.5, 10.0, 9.25, 8.78, 4.5, 5.5);
		System.out.println(String.join(", ", pointsToMarks.stream().map(Object::toString).collect(Collectors.toList())));

		pointsToMarks.replaceAll(p -> p * 5.0 / 10.0 + 1.0);
		System.out.println(String.join(", ", pointsToMarks.stream().map(Object::toString).collect(Collectors.toList())));

		pointsToMarks.sort(Double::compare);
		System.out.println(String.join(", ", pointsToMarks.stream().map(Object::toString).collect(Collectors.toList())));

		Collection<Integer> numbers = new ArrayList<>(Arrays.asList(1, 3, 52, 47, 8, 2, 15, 38, 15, 7, 42, 31, 43, 5));
		System.out.println(String.join(", ", numbers.stream().map(Object::toString).collect(Collectors.toList())));

		numbers.removeIf(n -> n > 20);
		System.out.println(String.join(", ", numbers.stream().map(Object::toString).collect(Collectors.toList())));

		Iterable<String> students = Arrays.asList("strut1", "touwm1");
		for (String student : students)
			System.out.printf("By for loop: %s%n", student);

		students.forEach(n -> System.out.printf("By lambda expression: %s%n", n));

		Map<String, String> studentNames = new HashMap<>(2);
		studentNames.put("strut1", "Strub, Thomas");
		studentNames.put("touwm1", "Touw, Marc");
		studentNames.putIfAbsent("touwm1", "not added");

		studentNames.forEach((k, v) -> System.out.printf("%s: %s; ", k, v));
		System.out.println();

		System.out.printf("weidj1: %s%n", studentNames.getOrDefault("weidj1", "n/a"));

		studentNames.merge("strut1", " Reto", String::concat);

		studentNames.forEach((k, v) -> System.out.printf("%s: %s; ", k, v));
		System.out.println();

		studentNames.replaceAll((k, v) -> String.format("%s (%s)", v, k));

		studentNames.forEach((k, v) -> System.out.printf("%s: %s; ", k, v));
		System.out.println();

		int pageSize = 3;
		int pageIndex = 1;
		Collection<String> names = Arrays.asList("Heinrich", "Joschua", "Hans", "Peter", "Hella", "Marc", "Hagrid", "Erich", "Heinz", "Thomas", "Heidi", "Marianne", "Heinrich", "Janick", "Heinz", "Reto", "Hermione");
		System.out.println(String.join(", ", names));

		Collection<String> namesH = names.stream()
																		 .parallel()
																		 .distinct()
																		 .map(String::toUpperCase)
																		 .peek(n -> System.out.printf("%s, ", n))
																		 .filter(l -> l.startsWith("H"))
																		 .sorted()
																		 .skip(pageIndex * pageSize).limit(pageSize)
																		 .collect(Collectors.toList());
		System.out.println();

		System.out.println(String.join(", ", namesH));
	}
}
