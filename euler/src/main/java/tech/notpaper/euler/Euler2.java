package tech.notpaper.euler;

import tech.notpaper.euler.util.Fibonacci;

public class Euler2 {
	
	/**
	 * Finds the sum of all even fibonacci numbers less than the limit
	 * 
	 * @param limit the limit for fibonacci generation
	 * @return the sum of all such numbers
	 */
	public static long sumOfEvenFibsLessThan(long limit) {
		long sum = 0L;
		
		for(Long l : new Fibonacci(limit)) {
			if (isEven(l)) {
				sum += l;
			}
		}
		
		return sum;
	}
	
    static boolean isEven(long n) {
    	return n % 2 == 0;
    }
}