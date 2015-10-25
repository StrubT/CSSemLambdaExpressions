package ch.bfh.cssem.lambdaexpressions;

/**
 * Class with tests for the {@link SampleInterface}.
 *
 * @author strut1 &amp; touwm1
 */
public final class SampleClass {

	private SampleClass() {}

	/**
	 * Main method / entry point to the application.
	 * Runs the tests.
	 *
	 * @param args command-line arguments (none used)
	 */
	public static void main(String... args) {
		new SampleClass().run();
	}

	/**
	 * Runs the tests.
	 */
	public void run() {

		SampleInterface.printHelloWorldStatic();

		SampleClass.printHelloWorld(new SampleClass.StaticNestedImplementation()); // static nested class (pre-Java 8)

		SampleClass.printHelloWorld(this.new InnerImplementation()); // inner class (pre-Java 8)

		class LocalImplementation implements SampleInterface { // local class (pre-Java 8)

			@Override
			public String getImplementationName() {
				return "local class";
			}
		}

		SampleClass.printHelloWorld(new LocalImplementation()); // local class (pre-Java 8)

		SampleClass.printHelloWorld(new SampleInterface() { // anonymous (inner) class (pre-Java 8)
			@Override
			public String getImplementationName() {
				return "anonymous (inner) class";
			}
		});

		SampleClass.printHelloWorld(() -> "lambda expression"); // lambda expression (Java 8)
	}

	private static void printHelloWorld(SampleInterface sample) {

		//System.out.printf("%s: ", sample.getImplementationName());
		sample.printHelloWorld();
	}

	private static class StaticNestedImplementation implements SampleInterface { // static nested class (pre-Java 8)

		@Override
		public String getImplementationName() {
			return "static nested class";
		}
	}

	private class InnerImplementation implements SampleInterface { // inner class (pre-Java 8)

		@Override
		public String getImplementationName() {
			return "inner class";
		}
	}
}
