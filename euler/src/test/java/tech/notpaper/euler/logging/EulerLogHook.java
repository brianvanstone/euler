package tech.notpaper.euler.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class EulerLogHook extends TestWatcher {
	
	private static final String logname = "tech.notpaper.euler";
	
	private static Logger me = null;
	
	private Logger logger = getLogger();
	
	public EulerLogHook() { }

	public static Logger getLogger() {
		if (me == null) {
			me = Logger.getLogger(logname);
			me.setLevel(Level.INFO);
		}
		
		return me;
	}
	
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