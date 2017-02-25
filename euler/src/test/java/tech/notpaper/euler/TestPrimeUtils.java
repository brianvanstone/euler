package tech.notpaper.euler;

import org.junit.Test;

import tech.notpaper.euler.util.primes.PrimeUtils;

public class TestPrimeUtils {
	
	@Test
	public void testMillerRabin() {
		for (int i = 0; i < 100; i++) {
			if (PrimeUtils.MillerRabin.isPrime(i)) {
				System.out.println(i);
			}
		}
	}

}
