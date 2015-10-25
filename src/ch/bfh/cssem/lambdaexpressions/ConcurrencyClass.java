package ch.bfh.cssem.lambdaexpressions;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrencyClass {

	public static void main(String... args) {

		String threadName0 = Thread.currentThread().getName(); // Get name of main Thread
		System.out.println("Hello " + threadName0);

		Thread th1 = new Thread(() -> {
			String threadName1 = Thread.currentThread().getName(); // Get name of Thread 1
			System.out.println("Hello " + threadName1);
		});

		Thread th2 = new Thread(() -> {
			String threadName2 = Thread.currentThread().getName(); // Get name of Thread 2
			System.out.println("Hello " + threadName2);
		});

		th1.start();
		th2.start();

		ExecutorService executor = Executors.newSingleThreadExecutor();  // Create Executor Service (Pool)
		Future<String> future = executor.submit(() -> {                 // Create Future in Pool
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
			return "Return from Future";
		});

		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
