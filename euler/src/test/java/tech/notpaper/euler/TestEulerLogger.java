package tech.notpaper.euler;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TestEulerLogger {
	
	private static final String logname = "TestEuler";
	
	private static Logger me = null;
	
	private TestEulerLogger() { }

	public static Logger getLogger() {
		if (me == null) {
			me = Logger.getLogger(logname);
			me.setLevel(Level.INFO);
		}
		
		return me;
	}
	
	public static class EulerLogHook extends TestWatcher {
		
		private Logger logger = getLogger();
		
		@Override
		protected void starting(Description description) {
			logger.log(Level.INFO, description.getDisplayName() + " starts");
			super.starting(description);
		}
		
		@Override
		protected void finished(Description description) {
			logger.log(Level.INFO, description.getDisplayName() + " finishes");
			super.finished(description);
		}
		
		@Override
		protected void failed(Throwable e, Description description) {
			logger.log(Level.SEVERE, description.getDisplayName(), e);
			super.failed(e, description);
	    }
	}
}
