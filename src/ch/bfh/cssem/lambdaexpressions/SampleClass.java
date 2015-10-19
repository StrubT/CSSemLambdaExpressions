package ch.bfh.cssem.lambdaexpressions;

public final class SampleClass {

	private SampleClass() {}

	public static void main(String... args) {

		SampleInterface.staticFunction();

		SampleClass.myFunction(new SampleInterface() { // anonymous class (pre-Java 8)
			@Override
			public void abstractFunction() {
				System.out.println("Hello, World! by anonymous class");
			}
		});

		SampleClass.myFunction(() -> System.out.println("Hello, World! by lambda expression")); // lambda expression (Java 8)
	}

	private static void myFunction(SampleInterface sample) {

		sample.abstractFunction();
		sample.defaultFunction();
	}
}
