package solution;

import java.math.BigInteger;
import java.util.List;

public class Accumulator {

	public BigInteger sum(List<BigInteger> numbers) {
		// It doesn't make sense to do this operation in parallel, as it very
		// light
		return numbers.stream().reduce((a, b) -> a.add(b)).get();
	}
}
