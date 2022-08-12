package main.java.design_patterns.creational.singleton.threadsafe;

public class ThreadSafeLogger {

    private static volatile ThreadSafeLogger logger;
    public String value;
    private ThreadSafeLogger(String value) {
        this.value = value;
    }

    public static ThreadSafeLogger getLogger(String value) {
        ThreadSafeLogger result = logger;
        if(result != null)
            return result;
        synchronized (ThreadSafeLogger.class) {
            if(logger == null) {
                logger = new ThreadSafeLogger(value);
            }
            return logger;
        }
    }
}
