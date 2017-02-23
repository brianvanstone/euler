package tech.notpaper.euler.util;

import java.util.Iterator;

public class Fibonacci implements Iterable<Long> {
	
	private long limit;
	
	/**
	 * Just like normal Fibonacci iteration, but will be
	 * empty when the limit is reached
	 * @param limit
	 */
	public Fibonacci(long limit) {
		this.limit = limit;
	}
	
	/**
	 * Yields numbers in the fibonacci sequence. Will stop returning values when
	 * the inherent limits of {@link Long} are encountered
	 */
	public Fibonacci() {
		this(Long.MAX_VALUE);
	}

	public Iterator<Long> iterator() {
		return new FibonacciIterator();
	}

	private class FibonacciIterator implements Iterator<Long> {
		
		private long lastOne = 0;
		private long lastTwo = 1;

		public boolean hasNext() {
			return lastOne + lastTwo < limit;
		}

		public Long next() {
			long result = lastOne + lastTwo;
			lastOne = lastTwo;
			lastTwo = result;
			
			return result;		
		}
	}
}
