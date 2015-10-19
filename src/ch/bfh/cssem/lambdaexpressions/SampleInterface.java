package ch.bfh.cssem.lambdaexpressions;

@FunctionalInterface
public interface SampleInterface {

	void abstractFunction();  // only one abstract function allowed

	default void defaultFunction() {  // Java 8: default functions allowed
		System.out.println("Hello, World! by default function");
	}

	static void staticFunction() {  // Java 8: static functions allowed
		System.out.println("Hello, World! by static function");
	}
}
