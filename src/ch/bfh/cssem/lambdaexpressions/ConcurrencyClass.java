package ch.bfh.cssem.lambdaexpressions;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Class with tests for the concurrency framework.
 *
 * @author strut1 &amp; touwm1
 */
public class ConcurrencyClass {

	/**
	 * Main method / entry point to the application.
	 * Runs the tests.
	 *
	 * @param args command-line arguments (none used)
	 */
	public static void main(String... args) {

		String threadName0 = Thread.currentThread().getName(); // get name of main thread
		System.out.println("Hello " + threadName0);

		Thread th1 = new Thread(() -> {
			String threadName1 = Thread.currentThread().getName(); // get name of thread 1
			System.out.println("Hello " + threadName1);
		});

		Thread th2 = new Thread(() -> {
			String threadName2 = Thread.currentThread().getName(); // get name of thread 2
			System.out.println("Hello " + threadName2);
		});

		th1.start();
		th2.start();

		ExecutorService executor = Executors.newSingleThreadExecutor(); // create ExecutorService (thread pool)
		Future<String> future = executor.submit(() -> { // create Future in pool
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
			return "Return from Future";
		});

		try {
			System.out.println(future.get());

		} catch (ExecutionException | InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
