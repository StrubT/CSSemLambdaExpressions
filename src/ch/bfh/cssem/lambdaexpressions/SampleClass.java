package ch.bfh.cssem.lambdaexpressions;

public final class SampleClass {

	private SampleClass() {}

	public static void main(String... args) {

		SampleInterface.staticMethod();

		SampleClass.myMethod(new SampleInterface() { // anonymous class (pre-Java 8)
			@Override
			public void abstractMethod() {
				System.out.println("Hello, World! by anonymous class");
			}
		});

		SampleClass.myMethod(() -> System.out.println("Hello, World! by lambda expression")); // lambda expression (Java 8)
	}

	private static void myMethod(SampleInterface sample) {

		sample.abstractMethod();
		sample.defaultMethod();
	}
}
