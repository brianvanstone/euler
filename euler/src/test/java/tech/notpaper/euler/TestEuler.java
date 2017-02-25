package tech.notpaper.euler;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

import tech.notpaper.euler.util.math.PrimeUtils;

public class TestEuler {
	
	private Logger logger = TestEulerLogger.getLogger();
	
	@Rule
	public TestWatcher logHook = new TestEulerLogger.EulerLogHook();
	
	public TestEuler() { }
	
	@Before
	public void beforeTest() {
		//nothing for now, maybe implement test timing
	}
	
	@After
	public void afterTest() {
		//nothing for now
	}
	
	//@Test
	public void test1() {
		assertEquals(233168, Euler1.sumOfMultiplesBelow(3, 5, 1000));
	}
	
	//@Test
	public void test2() {
		assertEquals(4613732L, Euler2.sumOfEvenFibsLessThan(4000000L));
	}
	
	@Test
	public void test3() {
		System.out.println(PrimeUtils.PollardRho.primeFactorOf(10403));
	}
}
