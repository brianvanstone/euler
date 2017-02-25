package tech.notpaper.euler.util.primes;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

import javax.naming.OperationNotSupportedException;

public class PrimeUtils {
	
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
			
			if (n <= 1) {
				return false;
			}
			
			if (n <= 3) {
				return true;
			}
			
			if (n % 2 == 0) {
				return false;
			}
			
			
			long d = n - 1;
			long r = 0L;
			while(d % 2 == 0) {
				d = d / 2L;
				r += 1L;
			}
			
			assertEquals((long) Math.pow(2, r)*d, n-1);
			
			ThreadLocalRandom rand = ThreadLocalRandom.current();
			
			int k = 30;
			while(k > 0) {
				k -= 1;
				long a = rand.nextLong(2, n-1);
				long x = (long )Math.pow(a, d) % n;
				if (x == 1 || x == n-1) {
					continue;
				}
				
				boolean cont = false;
				for(int _ = 0; _ < r; _++) {
					x = (long)Math.pow(x, 2) % n;
					if (x == 1) {
						return false;
					}
					
					if (x == n-1) {
						cont = true;
						break;
					}
				}
				
				if (cont) {
					continue;
				}
				
				return false;
			}
			
			return true;
		}
		
		/**
		 * Implementation of the Miller-Rabin primality test.
		 * See <a href="https://en.wikipedia.org/wiki/Miller%E2%80%93
		 * Rabin_primality_test#Computational_complexity">this page</a>
		 * for more information.
		 * @param n the number to be tested
		 * @return true if the number is probably prime, false otherwise
		 */
		public static boolean isPrime(int n) {
			return isPrime((long) n);
		}
		
		public static boolean isPrime(BigInteger n) throws OperationNotSupportedException {
			throw new OperationNotSupportedException("support coming soon!");
		}
	}
}
