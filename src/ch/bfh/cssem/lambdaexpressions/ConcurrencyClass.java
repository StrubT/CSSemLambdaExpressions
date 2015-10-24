package ch.bfh.cssem.lambdaexpressions;

public class ConcurrencyClass {

	public static void main(String[] args) {

		String threadName0 = Thread.currentThread().getName();
		System.out.println("Hello " + threadName0);

		Thread th1 = new Thread(() -> {
			String threadName1 = Thread.currentThread().getName();
			System.out.println("Hello " + threadName1);
		});

		Thread th2 = new Thread(() -> {
			String threadName2 = Thread.currentThread().getName();
			System.out.println("Hello " + threadName2);
		});

		th1.start();
		th2.start();

	}
}
