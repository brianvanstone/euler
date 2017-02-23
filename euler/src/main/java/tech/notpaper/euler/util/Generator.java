package tech.notpaper.euler.util;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

public class Generator<F extends GeneratorCallback<T>, T> implements Iterable<T> {
	
	private F callback;
	private long limit = Long.MAX_VALUE;
	
	public Generator(F callback) {
		this.callback = callback;
	}
	
	public Generator(F callback, int limit) {
		this(callback);
		this.limit = limit;
	}
	
	public Generator(F callback, long limit) {
		this(callback);
		this.limit = limit;
	}

	public Iterator<T> iterator() {
		return new GeneratorIterator<F>();
	}
	
	private class GeneratorIterator<Z extends GeneratorCallback<T>> implements Iterator<T> {
		
		private Thread thread;
		private long count = 0L;
		
		public GeneratorIterator() {
			thread = new Thread(callback, "test_gen");
			thread.start();
		}

		@Override
		public boolean hasNext() {
			return count < limit;
		}

		@Override
		public T next() {
			//wait for a value to be ready
			try {
				callback.activeSignal.await();
			} catch (InterruptedException e) { } // swallow
			
			//count by 1
			count++;
			
			//set value aside
			T value = callback.curValue;
			
			//reset activeSignal, assuming callback is busy
			callback.activeSignal = new CountDownLatch(1);
			
			//tell callback to stop waiting
			callback.waitSignal.countDown();
			
			//return the value fetched
			return (T) value;
		}
	}
}
