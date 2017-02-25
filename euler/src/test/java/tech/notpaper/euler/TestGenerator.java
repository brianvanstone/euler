package tech.notpaper.euler;

import org.junit.Test;

import tech.notpaper.euler.util.Generator;
import tech.notpaper.euler.util.GeneratorCallback;

public class TestGenerator {

	@Test
	public void testNaturalNumbers() {
		Generator<NaturalNumbers, Long> gen = new Generator<>(new NaturalNumbers(), 20);
		
		for(Long l : gen) {
			System.out.println(l);
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
		Generator<Fibonacci, Long> fib = new Generator<>(new Fibonacci(), 50);
		
		for(long l : fib) {
			System.out.println(l);
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
