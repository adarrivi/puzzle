package solution;

import java.math.BigInteger;

public class PseudoFibonacciCachedSequence implements IntegerSequence {

	private static final LimitedCache CACHE = new LimitedCache();

	@Override
	public BigInteger calculate(int size) {
		BigInteger value = (BigInteger) CACHE.get(size);
		if (value != null) {
			return value;
		}
		if (size <= 1) {
			return BigInteger.ZERO;
		}
		if (size == 2) {
			return BigInteger.ONE;
		}

		value = calculate(size - 1).add(calculate(size - 2));
		CACHE.put(size, value);
		return value;
	}

}
