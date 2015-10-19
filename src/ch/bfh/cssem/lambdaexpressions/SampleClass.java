package ch.bfh.cssem.lambdaexpressions;

public class SampleClass {

	public static void main(String[] args) {

		SampleInterface.staticFunction();

		myFunction(new SampleInterface() { // anonymous class (pre-Java 8)
			@Override
			public void abstractFunction() {
				System.out.println("Hello, World! by anonymous class");
			}
		});

		myFunction(() -> System.out.println("Hello, World! by lambda expression")); // lambda expression (Java 8)
	}

	public static void myFunction(SampleInterface sample) {

		sample.abstractFunction();
		sample.defaultFunction();
	}
}
