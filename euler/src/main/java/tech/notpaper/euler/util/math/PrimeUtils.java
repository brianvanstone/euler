package tech.notpaper.euler.util.math;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

import javax.naming.OperationNotSupportedException;

import tech.notpaper.euler.util.Generator;
import tech.notpaper.euler.util.GeneratorCallback;

public class PrimeUtils {
	//TODO These implementations need work
	
	
	public static class MillerRabin {
		/**
		 * Implementation of the Miller-Rabin primality test.
		 * See <a href="https://en.wikipedia.org/wiki/Miller%E2%80%93
		 * Rabin_primality_test#Computational_complexity">this page</a>
		 * for more information.
		 * @param n the number to be tested
		 * @return true if the number is probably prime, false otherwise
		 */
		public static boolean isPrime(long n) {
			
			// n < 1 is not prime
			if (n <= 1) {
				return false;
			}
			
			// 2 and 3 are prime
			if (n <= 3) {
				return true;
			}
			
			// all other evens are not prime
			if (n % 2 == 0) {
				return false;
			}
			
			// now n is odd and n > 3
			//  proceed with Miller Rabin primality test
			
			// represent n-1 as d2^r with d odd by factoring powers of 2 
			long d = n - 1;
			int r = 0;
			while(d % 2L == 0) {
				d /= 2L;
				r++;
			}
			
			// assert that the values we found hold true for d2^r == n-1
			assertEquals((long) Math.pow(2, r)*d, n-1);
			
			ThreadLocalRandom rand = ThreadLocalRandom.current();
			
			// k times
			for (int k = 0; k < 1000; k++) {
				// pick a random integer a in the range [2, n − 2]
				long a = rand.nextLong(2, n-1);
				
				// x = a^d mod n
				long x = (long )Math.pow(a, d) % n;
				
				if (x == 1 || x == n-1) {
					continue;
				}
				
				boolean cont = false;
				// r-1 times
				for(int rt = 0; rt < r-1; rt++) {
					
					// x = x^2 mod n
					x = (long)Math.pow(x, 2) % n;
					
					if (x == 1) {
						// composite number
						return false;
					}
					
					if (x == n-1) {
						// continue outer loop (k loop)
						cont = true;
						break;
					}
				}
				
				if (cont) {
					continue;
				}
				// composite number
				return false;
			}
			// probably prime, depending on k
			return true;
		}
		
		public static boolean isPrime(BigInteger n) throws OperationNotSupportedException {
			throw new OperationNotSupportedException("support coming soon!");
		}
	}
	
	public static class PollardRho {
		
		public static long primeFactorOf(long n) {
			if (n < 2) {
				return -1;
			}
			
			long xFixed = 2, cycleSize = 2, x = 2, factor = 1;
			
			while (factor == 1) {
				for (int count = 1; count <= cycleSize && factor <= 1; count++) {
					x = (x * x + 1) % n;
					factor = NumberUtils.gcd(x - xFixed, n);
				}

				cycleSize *= 2;
				xFixed = x;
			}
			
			return factor;
		}
	}
	
	
	/**
	 * Provides primes generated according to the Sieve of Erastosthenes. See
	 * <a href="https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Algorithm_and_variants">
	 * this link</a> for more information
	 *
	 */
	public static class SieveOfEratosthenes extends Generator<GeneratorCallback<Long>, Long> {
		
		public SieveOfEratosthenes() {
			super(new PrimeGen(Long.MAX_VALUE), Long.MAX_VALUE);
		}
		
		public SieveOfEratosthenes(long limit) {
			super(new PrimeGen(limit), limit);
		}
		
		private static class PrimeGen extends GeneratorCallback<Long> {
			private long limit;
			
			public PrimeGen(long limit) {
				this.limit = limit;
			}

			@Override
			public void go() throws InterruptedException {
				Map<Long, Boolean> A = new HashMap<>();
				
				for(long i = 2; i < Math.sqrt(limit); i++) {
					long iSq = (long) Math.pow(i, 2);
					if (A.getOrDefault(i, true)) {
						for (long multiplier = 0, j = iSq; j < limit; j = iSq+(multiplier*i), multiplier++) {
							A.put(j, false);
						}
						yield(i);
					}
				}
			}
		}
	}
}
