package tech.notpaper.euler;

import static org.junit.Assert.*;

import org.junit.Test;

import tech.notpaper.euler.util.math.PrimeUtils;

public class TestPrimeUtils {
	
	@Test
	public void testMillerRabin() {
		//count primes
		
		int j = 0;
		for(int i = 0; i < 1000; i++) {
			if (PrimeUtils.MillerRabin.isPrime(i)) {
				j++;
				System.out.println(i);
			}
		}
		
		assertEquals(168, j);
	}
	
	@Test
	public void testPollardRho() {
		assertEquals(101L, PrimeUtils.PollardRho.primeFactorOf(10403));
		assertEquals(-1, PrimeUtils.PollardRho.primeFactorOf(0));
		assertEquals(-1, PrimeUtils.PollardRho.primeFactorOf(1));
		assertEquals(-1, PrimeUtils.PollardRho.primeFactorOf(-1));
		assertEquals(3, PrimeUtils.PollardRho.primeFactorOf(3));
		assertEquals(101, PrimeUtils.PollardRho.primeFactorOf(101));
	}

}
