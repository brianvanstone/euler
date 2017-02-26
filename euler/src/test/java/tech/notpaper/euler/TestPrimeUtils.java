package tech.notpaper.euler;

import static org.junit.Assert.*;

import org.junit.Test;

import tech.notpaper.euler.util.math.PrimeUtils;

public class TestPrimeUtils {
	
	//@Test
	public void testMillerRabin() {
		//count primes
		
		int j = 0;
		for(int i = 0; i < 1000; i++) {
			if (PrimeUtils.MillerRabin.isPrime(i)) {
				j++;
			}
		}
		
		assertEquals(168, j);
	}
	
	//@Test
	public void testPollardRho() {
		
	}
	
	@Test
	public void testEratosthenes() {
		PrimeUtils.SieveOfEratosthenes primes = new PrimeUtils.SieveOfEratosthenes(100);
		
		int i = 0;
		for(long prime : primes) {
			i++;
			System.out.println(prime);
		}
		
		assertEquals(168, i);
	}

}
