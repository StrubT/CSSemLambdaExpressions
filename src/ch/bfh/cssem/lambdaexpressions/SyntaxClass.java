package ch.bfh.cssem.lambdaexpressions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SyntaxClass {

	private SyntaxClass() {}

	public static void main(String... args) {

		SyntaxClass.println(() -> String.format("Hello, World! at %s", LocalDateTime.now()));

		List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		List<Integer> even1 = SyntaxClass.even(numbers, (Integer n) -> {
			int mod = n % 2;
			return mod == 0;
		});
		List<Integer> even2 = SyntaxClass.even(numbers, (Integer n) -> n % 2 == 0);
		List<Integer> even3 = SyntaxClass.even(numbers, (n) -> n % 2 == 0);
		List<Integer> even4 = SyntaxClass.even(numbers, n -> n % 2 == 0);

		System.out.println(even1);
		System.out.println(even2);
		System.out.println(even3);
		System.out.println(even4);

		String separator = " ";
		List<String> elements = Arrays.asList("Hello,", "World!", "by", "strut1", "and", "touwm1");
		String joined1 = SyntaxClass.join(elements, (a, b) -> a + separator + b);
		String joined2 = SyntaxClass.join(elements, SyntaxClass::join);

		System.out.println(joined1);
		System.out.println(joined2);

		UnaryInterface<Integer, Integer> square = x -> x * x;
		System.out.println(square.execute(5));

		BinaryInterface<Integer, Integer, Integer> sum = (x, y) -> x + y;
		System.out.println(sum.execute(5, 7));
	}

	private static void println(NullaryInterface<String> message) {

		System.out.println(message.execute());
	}

	private static List<Integer> even(List<Integer> numbers, UnaryInterface<Integer, Boolean> predicate) {

		List<Integer> even = new ArrayList<>();
		for (Integer number : numbers)
			if (predicate.execute(number))
				even.add(number);

		return even;
	}

	private static String join(String string1, String string2) {

		return String.format("%s %s", string1, string2);
	}

	private static String join(List<String> elements, BinaryInterface<String, String, String> joiner) {

		String joined = !elements.isEmpty() ? elements.get(0) : null;
		for (int i = 1; i < elements.size(); i++)
			joined = joiner.execute(joined, elements.get(i));

		return joined;
	}
}
