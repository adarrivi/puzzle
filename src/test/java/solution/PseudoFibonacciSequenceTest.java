package solution;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import solution.PseudoFibonacciCachedSequence;

public class PseudoFibonacciSequenceTest {

	private PseudoFibonacciCachedSequence victim = new PseudoFibonacciCachedSequence();
	private int size;
	private BigInteger result;

	@Test
	public void negative_ReturnsZero() {
		givenSequence(-1);
		whenCalculate();
		thenExpect(0);
	}

	private void givenSequence(int size) {
		this.size = size;
	}

	private void whenCalculate() {
		result = victim.calculate(size);
	}

	private void thenExpect(int expectedResult) {
		Assert.assertEquals(expectedResult, result.longValue());
	}

	@Test
	public void zero_ReturnsZero() {
		givenSequence(0);
		whenCalculate();
		thenExpect(0);
	}

	@Test
	public void one_ReturnsZero() {
		givenSequence(1);
		whenCalculate();
		thenExpect(0);
	}

	@Test
	public void two_ReturnsOne() {
		givenSequence(2);
		whenCalculate();
		thenExpect(1);
	}

	@Test
	public void three_ReturnsTwo() {
		givenSequence(3);
		whenCalculate();
		thenExpect(1);
	}

	@Test
	public void ten_Returns34() {
		givenSequence(10);
		whenCalculate();
		thenExpect(34);
	}

}
