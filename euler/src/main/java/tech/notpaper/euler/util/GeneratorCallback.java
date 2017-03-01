package tech.notpaper.euler.util;

import java.util.concurrent.CountDownLatch;

public abstract class GeneratorCallback<T> implements Runnable {
	
	volatile T curValue = null;
	CountDownLatch activeSignal = new CountDownLatch(1);
	CountDownLatch waitSignal = new CountDownLatch(1);
	
	@Override
	public final void run() {
		try {
			this.go();
		} catch (InterruptedException e) { } // swallow
	}
	
	/**
	 * Implementations of {@link GeneratorCallback} should invoke
	 * this method from within {@link GeneratorCallback#go()} in
	 * order to present a value to be yielded to the iteration
	 * @param value
	 * @throws InterruptedException
	 */
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
