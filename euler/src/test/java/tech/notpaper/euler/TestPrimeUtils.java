package tech.notpaper.euler;

import static org.junit.Assert.*;

import org.junit.Test;

import tech.notpaper.euler.util.math.PrimeUtils;

public class TestPrimeUtils {
	
	@Test
	public void testMillerRabin() {
		assertTrue(PrimeUtils.MillerRabin.isPrime(1500450271));
	}
	
	@Test
	public void testPollardRho() {
		assertEquals(101L, PrimeUtils.PollardRho.primeFactorOf(10403));
	}

}
