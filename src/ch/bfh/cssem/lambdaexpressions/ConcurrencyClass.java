package ch.bfh.cssem.lambdaexpressions;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Class with tests for the concurrency framework.
 *
 * @author strut1 &amp; touwm1
 */
public final class ConcurrencyClass {

	private ConcurrencyClass() {}

	/**
	 * Main method / entry point to the application.
	 * Runs the tests.
	 *
	 * @param args command-line arguments (none used)
	 */
	public static void main(String... args) {

		// *** PROCEDURAL ***

		String threadNameMain = Thread.currentThread().getName(); // get name of main thread
		System.out.printf("Hello! from procedural call on thread %s%n", threadNameMain);

		// *** RUNNABLE ***

		Runnable runnableAnonymous = new Runnable() { // create an anonymous class implementing the interface
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName(); // get name of main thread inside runnable
				System.out.printf("Hello! from runnable anonymous class on thread %s%n", threadName);
			}
		};

		Runnable runnableLambda = () -> { // create a lambda expression "implementing" the interface
			String threadName = Thread.currentThread().getName();
			System.out.printf("Hello! from runnable lambda expression on thread %s%n", threadName);
		};

		runnableAnonymous.run();
		runnableLambda.run();

		// *** THREAD ***

		Thread threadAnonymousExtended = new Thread() { // create an anonymous class extending the class
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName(); // get name of thread
				System.out.printf("Hello! from extended thread %s%n", threadName);
			}
		};

		Thread threadAnonymousImplemented = new Thread(new Runnable() { // create an anonymous class implementing the interface
			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.printf("Hello! from thread with runnable anonymous class %s%n", threadName);
			}
		});

		Thread threadLambda = new Thread(() -> { // create a lambda expression "implementing" the interface
			String threadName = Thread.currentThread().getName();
			System.out.printf("Hello! from thread with runnable lambda expression %s%n", threadName);
		});

		threadAnonymousExtended.start();
		threadAnonymousImplemented.start();
		threadLambda.start();

		try {
			threadAnonymousExtended.join();
			threadAnonymousImplemented.join();
			threadLambda.join();

		} catch (InterruptedException ex) {
			System.out.printf("cannot join threads: %s%n", ex);
		}

		// *** CALLABLE ***

		Callable<String> callableAnonymous = new Callable<String>() { // create an anonymous class implementing the interface
			@Override
			public String call() {
				String threadName = Thread.currentThread().getName(); // get name of main thread inside callable
				System.out.printf("Hello! from callable anonymous class on thread %s%n", threadName);
				return "return value from callable anonymous class"; // return value to caller
			}
		};

		Callable<String> callableLambda = () -> { // create a lambda expression "implementing" the interface
			String threadName = Thread.currentThread().getName();
			System.out.printf("Hello! from callable lambda expression on thread %s%n", threadName);
			return "return value from callable lambda expression";
		};

		try {
			System.out.printf(">%s%n", callableAnonymous.call());
			System.out.printf(">%s%n", callableLambda.call());

		} catch (Exception ex) {
			System.out.printf("cannot call callable: %s%n", ex);
		}

		// *** FUTURE ***

		ExecutorService executor1 = Executors.newSingleThreadExecutor(); // create ExecutorService (managing single thread)
		ExecutorService executor2 = Executors.newCachedThreadPool(); // create ExecutorService (cached thread pool)

		Future<String> future11 = executor1.submit(new Callable<String>() { // create Future in pool
			@Override
			public String call() throws Exception {
				String threadName = Thread.currentThread().getName(); // get name of pool thread
				System.out.printf("Hello! from future 1 on pool 1 on thread %s%n", threadName);
				return "return value from future 1 in pool 1"; // return value to caller
			}
		});

		Future<String> future12 = executor1.submit(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.printf("Hello! from future 2 in pool 1 on thread %s%n", threadName);
			return "return value from future 2 in pool 1";
		});

		Future<String> future21 = executor2.submit(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.printf("Hello! from future 1 in pool 2 on thread %s%n", threadName);
		}, "return value from future 2 in pool 2");

		Future<?> future22 = executor2.submit(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.printf("Hello! from future 2 in pool 2 on thread %s%n", threadName);
		}); // no return value

		try {
			System.out.printf(">%s%n", future11.get());
			System.out.printf(">%s%n", future12.get());
			System.out.printf(">%s%n", future21.get());
			System.out.printf(">%s%n", future22.get()); // will always be null

		} catch (ExecutionException | InterruptedException ex) {
			System.out.printf("cannot get future values: %s%n", ex);
		}
	}
}
