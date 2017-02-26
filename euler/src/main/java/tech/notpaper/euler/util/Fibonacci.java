package tech.notpaper.euler.util;

public class Fibonacci extends Generator<GeneratorCallback<Long>, Long> {
	
	public Fibonacci() {
		super(new FibGen());
	}
	
	public Fibonacci(long limit) {
		super(new FibGen(), limit);
	}

	public static class FibGen extends GeneratorCallback<Long> {
		private long lastOne = 1L;
		private long lastTwo = 0L;
		
		@Override
		public void go() throws InterruptedException {
			while(true) {
				long val = lastOne + lastTwo;
				yield(val);
				lastOne = lastTwo;
				lastTwo = val;
			}
		}
	}
}
