package tech.notpaper.euler.util;

import java.util.Iterator;

public abstract class Generator<T> implements Iterable<T> {
	
	private T nextVal;
	private boolean waiting = false;

	public Iterator<T> iterator() {
		this.go();
		return new GeneratorIterator<T>(this);
	}
	
	private class GeneratorIterator<F> implements Iterator<F> {
		
		private Generator<F> generator;
		
		public GeneratorIterator(Generator<F> generator) {
			this.generator = generator;
		}

		public boolean hasNext() {
			return generator.hasNext();
		}

		@SuppressWarnings("unchecked")
		public F next() {
			waiting = false;
			return (F) nextVal;
		}
		
	}
	
	public boolean hasNext() {
		return true;
	}
	
	protected synchronized final void yield(T value) throws InterruptedException {
		nextVal = value;
		while(waiting) {
			wait();
		}
	}
	
	protected abstract void go();
}
