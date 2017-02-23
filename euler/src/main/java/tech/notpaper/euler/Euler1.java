package tech.notpaper.euler;

public class Euler1 {
    
	/**
	 * Given two numbers, x and y, return the sum of all multiples
	 * of these numbers which do not themselves exceed limit
	 * @param x The first number to base multiples from
	 * @param y The second number to base multiples from
	 * @param limit The limit under which multiples are bounded
	 * @return The sum of all multiples matching the input parameters
	 */
	public static long sumOfMultiplesBelow(long x, long y, long limit) {
		
		//vars to add and return eventually
		long xSum = 0L;
		long ySum = 0L;
		
		//get xSum
		long i = 1;
		long mx = x*i;
		while(mx < limit) {
			xSum += mx;
			mx = x*++i;
		}
		
		//get ySum
		i = 1;
		long my = y*i;
		while(my < limit) {
			ySum += my;
			my = y*++i;
		}
		
		return xSum + ySum;
	}
}