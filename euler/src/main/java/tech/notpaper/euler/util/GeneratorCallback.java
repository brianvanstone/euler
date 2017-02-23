package tech.notpaper.euler.util;

import java.util.concurrent.CountDownLatch;

public abstract class GeneratorCallback<T> implements Runnable {
	
	volatile T curValue = null;
	CountDownLatch activeSignal = new CountDownLatch(1);
	CountDownLatch waitSignal = new CountDownLatch(1);
	
	public final void run() {
		try {
			this.go();
		} catch (InterruptedException e) { } // swallow
	}
	
	public final void yield(T value) throws InterruptedException {
		//update the value
		curValue = value;
		//drop the active signal
		activeSignal.countDown();
		//wait to stop waiting
		waitSignal.await();
		//reset wait latch for next time
		waitSignal = new CountDownLatch(1);
	}
	
	/**
	 * This method should continuously progress through a sequence
	 * of values, invoking yield each time such a value is
	 * discovered
	 * @throws InterruptedException 
	 */
	public abstract void go() throws InterruptedException;
}
