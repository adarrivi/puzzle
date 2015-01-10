package solution;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import solution.Accumulator;

public class AccumulatorTest {

	private Accumulator victim = new Accumulator();

	private List<BigInteger> inputValues;
	private BigInteger result;

	@Test
	public void tenValues_AddsAll() {
		givenValues(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		whenSum();
		thenExpect(55);
	}

	private void givenValues(Integer... values) {
		inputValues = Arrays.asList(values).stream().map(value -> BigInteger.valueOf(value))
				.collect(Collectors.toList());
	}

	private void whenSum() {
		result = victim.sum(inputValues);
	}

	private void thenExpect(int expectedResult) {
		Assert.assertEquals(expectedResult, result.longValue());
	}

}
