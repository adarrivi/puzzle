package solution.internal;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import solution.internal.Main;

public class MainIntegrationTest {

	private static final BigInteger SOLUTION = new BigInteger("367683528842993474303");

	@Test
	public void integrationTest() throws InterruptedException {
		BigInteger result = Main.calculate();
		System.out.println("Solution found " + result.toString());
		Assert.assertTrue(SOLUTION.equals(result));
	}

}
