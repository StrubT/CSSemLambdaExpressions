package ch.bfh.cssem.lambdaexpressions;

/**
 * An example {@link FunctionalInterface}.
 *
 * @author strut1 &amp; touwm1
 */
@FunctionalInterface
public interface SampleInterface {

	/**
	 * Gets the name of the interface implementation.
	 *
	 * @return name of the interface implementation
	 */
	String getImplementationName();  // exactly one abstract method required / allowed

	boolean equals(Object obj); // already defined in java.lang.Object, allowed

	/**
	 * Prints {@code Hello, World!} with the name of the implementation.
	 */
	default void printHelloWorld() {  // default methods allowed
		System.out.println(String.format("Hello, World! by %s", this.getImplementationName()));
	}

	/**
	 * Prints {@code Hello, World!}.
	 */
	static void printHelloWorldStatic() {  // static methods allowed
		System.out.println("Hello, World! by static function");
	}
}
