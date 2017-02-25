package tech.notpaper.euler.util.math;

public class NumberUtils {
	
	public static long gcd(long a, long b) {
		long remainder = 0;
		while (b != 0) {
			remainder = a % b;
			a = b;
			b = remainder;
		}
		return a;
	}
}
