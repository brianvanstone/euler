package tech.notpaper.euler;

import static org.junit.Assert.assertEquals;

import tech.notpaper.euler.util.math.PrimeUtils;

public class Euler3 {
	
	public static long largestPrimeFactorOf(long n) {
		
		long lastN = n;
		n = PrimeUtils.PollardRho.primeFactorOf(n);
		
		long largest = n;
		while(lastN != n) {
			lastN = n;
			n = PrimeUtils.PollardRho.primeFactorOf(n);
			
			if (n > largest) {
				largest = n;
			}
		}
		
		return largest;
	}
	
}
