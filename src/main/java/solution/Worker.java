package solution;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import solution.internal.Source;

public class Worker implements Runnable {

	public static final List<BigInteger> numbers = new LinkedList<>();

	private final int lineIndex;
	private final CountDownLatch latch;

	private IntegerSequence sequence;

	public Worker(int lineIndex, CountDownLatch latch, IntegerSequence sequence) {
		this.lineIndex = lineIndex;
		this.latch = latch;
		this.sequence = sequence;
	}

	@Override
	public void run() {
		try {
			long start = System.currentTimeMillis();
			int numberIndex = Source.getValueAtLine(lineIndex);
			synchronized (numbers) {
				BigInteger secret = sequence.calculate(numberIndex);
				numbers.add(secret);
			}
			latch.countDown();

			System.out.printf("Secret calculated in %d ms. Remaining secrets to calculate: %d%n",
					System.currentTimeMillis() - start, latch.getCount());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
