package ch.bfh.cssem.lambdaexpressions;

@FunctionalInterface
public interface SampleInterface {

	void abstractMethod();  // exactly one abstract method required / allowed

	boolean equals(Object obj); // already defined in java.lang.Object, allowed

	default void defaultMethod() {  // default methods allowed
		System.out.println("Hello, World! by default function");
	}

	static void staticMethod() {  // static methods allowed
		System.out.println("Hello, World! by static function");
	}
}
