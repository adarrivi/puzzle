package solution.internal;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import solution.Accumulator;
import solution.PseudoFibonacciCachedSequence;
import solution.Worker;

public class Main {

	public static final int WORKLOAD = 100;

	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();

		BigInteger total = calculate();

		System.out.printf("Total running time %d%n", System.currentTimeMillis() - start);
		System.out.printf("Secret: %d%n", total);
		System.out.printf(
				"Congratulations! You have solved our secret! Now go to http://%d.plumbr.eu to claim your prize%n",
				total);
		System.out.printf("If the url above does not open, may be you haven't solved all problems yet?%n");
	}

	static BigInteger calculate() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(WORKLOAD);
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		PseudoFibonacciCachedSequence sequence = new PseudoFibonacciCachedSequence();

		for (int i = 0; i < WORKLOAD; i++) {
			executorService.submit(new Worker(i, latch, sequence));
		}

		latch.await();

		BigInteger total = new Accumulator().sum(Worker.numbers);
		executorService.shutdown();

		return total;
	}

}
