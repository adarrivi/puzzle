package original.fast;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Accumulator {
  private volatile BigInteger result = BigInteger.ZERO;
  private static final Object lock = new Object();

  public BigInteger sum(List<BigInteger> numbers) {
    ExecutorService executorService = Executors.newFixedThreadPool(8);
    for (final BigInteger number : numbers) {
      executorService.submit(new Runnable() {
        @Override
        public void run() {
          synchronized (lock) {
            result = result.add(number);
          }
        }
      });
    }
    executorService.shutdown();
    try {
      executorService.awaitTermination(10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return result;
  }
}
