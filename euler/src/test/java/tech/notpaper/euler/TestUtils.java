package tech.notpaper.euler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tech.notpaper.euler.util.Fibonacci;

public class TestUtils {

	@Test
	public void testFibonacci() {
		int[] partialFib = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};
		
		int i = 0;
		for(long term : new Fibonacci(partialFib.length)) {
			assertEquals(partialFib[i++], term);
		}
		
		assertEquals(partialFib.length, i);
	}
}
