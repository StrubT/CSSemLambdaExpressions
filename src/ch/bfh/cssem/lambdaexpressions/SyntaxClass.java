package ch.bfh.cssem.lambdaexpressions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class SyntaxClass {

	private SyntaxClass() {}

	public static void main(String... args) {

		Supplier<SyntaxClass> constructor = SyntaxClass::new; // constructor reference
		SyntaxClass instance = constructor.get();

		SyntaxClass.println(() -> String.format("Hello, World! at %s", LocalDateTime.now()));

		List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		List<Integer> even1 = SyntaxClass.filter(numbers, (Integer n) -> {
			int mod = n % 2;
			return mod == 0;
		});
		List<Integer> even2 = SyntaxClass.filter(numbers, (Integer n) -> n % 2 == 0);
		List<Integer> even3 = SyntaxClass.filter(numbers, (n) -> n % 2 == 0);
		List<Integer> even4 = SyntaxClass.filter(numbers, n -> n % 2 == 0);

		System.out.println(even1);
		System.out.println(even2);
		System.out.println(even3);
		System.out.println(even4);

		String separator = " ";
		List<String> elements = Arrays.asList("Hello,", "World!", "by", "strut1", "and", "touwm1");
		String joined1 = SyntaxClass.join(elements, (String a, String b) -> a + separator + b);
		String joined2 = SyntaxClass.join(elements, (a, b) -> a + separator + b);
		String joined3 = SyntaxClass.join(elements, instance::joinInstance); // instance method
		String joined4 = SyntaxClass.join(elements, SyntaxClass::joinStatic); // static method
		String joined5 = SyntaxClass.join(elements, String::concat); // instance method (special handling)
		String joined6 = SyntaxClass.join(elements, (_this, str) -> _this.concat(str));

		System.out.println(joined1);
		System.out.println(joined2);
		System.out.println(joined3);
		System.out.println(joined4);
		System.out.println(joined5);
		System.out.println(joined6);

		UnaryOperator<Integer> square = x -> x * x;
		System.out.println(square.apply(5));

		BinaryOperator<Integer> sum = (x, y) -> x + y;
		System.out.println(sum.apply(5, 7));
	}

	private static void println(Supplier<String> message) {

		System.out.println(message.get());
	}

	private static List<Integer> filter(List<Integer> numbers, Predicate<Integer> predicate) {

		List<Integer> filtered = new ArrayList<>();
		for (Integer number : numbers)
			if (predicate.test(number))
				filtered.add(number);

		return filtered;
	}

	private String joinInstance(String string1, String string2) {

		return String.format("%s %s", string1, string2);
	}

	private static String joinStatic(String string1, String string2) {

		return String.format("%s %s", string1, string2);
	}

	private static String join(List<String> elements, BinaryOperator<String> joiner) {

		String joined = !elements.isEmpty() ? elements.get(0) : null;
		for (int i = 1; i < elements.size(); i++)
			joined = joiner.apply(joined, elements.get(i));

		return joined;
	}
}
