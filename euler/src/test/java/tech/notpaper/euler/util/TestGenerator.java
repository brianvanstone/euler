package tech.notpaper.euler.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tech.notpaper.euler.util.Generator;
import tech.notpaper.euler.util.GeneratorCallback;

public class TestGenerator {

	@Test
	public void testNaturalNumbers() {
		Generator<NaturalNumbers, Long> gen = new Generator<>(new NaturalNumbers(), 1000000000L);
		
		long i = 1L;
		for(Long l : gen) {
			assertEquals(i++, l.longValue());
		}
	}
	
	class NaturalNumbers extends GeneratorCallback<Long> {
		@Override
		public void go() throws InterruptedException {
			
			for(long l = 1L; true; l++) {
				yield(l);
			}
		}
	}
	
	@Test
	public void testFib() {
		int[] partialFib = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};
		
		Generator<Fibonacci, Long> fib = new Generator<>(new Fibonacci(), partialFib.length);
		
		int i = 0;
		for(long l : fib) {
			assertEquals(partialFib[i++], l);
		}
	}
	
	class Fibonacci extends GeneratorCallback<Long> {
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
