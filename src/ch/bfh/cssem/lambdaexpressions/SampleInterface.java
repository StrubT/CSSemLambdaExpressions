package ch.bfh.cssem.lambdaexpressions;

@FunctionalInterface
public interface SampleInterface {

	String getImplementationName();  // exactly one abstract method required / allowed

	boolean equals(Object obj); // already defined in java.lang.Object, allowed

	default void printHelloWorld() {  // default methods allowed
		System.out.println(String.format("Hello, World! by %s", this.getImplementationName()));
	}

	static void printHelloWorldStatic() {  // static methods allowed
		System.out.println("Hello, World! by static function");
	}
}
