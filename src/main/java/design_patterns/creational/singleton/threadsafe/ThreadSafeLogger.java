package main.java.design_patterns.creational.singleton.threadsafe;

public class ThreadSafeLogger {
 // The volatile keyword now ensures that multiple threads handle the singleton instance correctly.
    private static volatile ThreadSafeLogger logger;
    public String value;
    private ThreadSafeLogger(String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    // The below implementation is the Double-Checked-Locking
    public static ThreadSafeLogger getLogger(String value) {
        // Note the local variable "localRef", which seems unnecessary. The effect of this is that in cases where "logger" is already initialized (i.e., most of the time), the volatile field is only accessed once (due to "return localRef;" instead of "return logger;"), which can improve the method's overall performance by as much as 40 percent.
        ThreadSafeLogger localRef = logger;
        if(localRef != null)
            return localRef;
        // Obtaining a lock in case two threads call getLogger() method
        synchronized (ThreadSafeLogger.class) {
            if(logger == null) {
                logger = new ThreadSafeLogger(value);
            }
            return logger;
        }
    }
}
