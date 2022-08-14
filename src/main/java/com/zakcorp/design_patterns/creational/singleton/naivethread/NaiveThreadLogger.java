package com.zakcorp.design_patterns.creational.singleton.naivethread;

// Logger with a Singleton Design Pattern -> Note this is not thread-safe
public class NaiveThreadLogger {
    private static NaiveThreadLogger logger;
    public String value;

    private NaiveThreadLogger(String value) {
        // To stop the execution of the thread for one second and then continue again
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static NaiveThreadLogger getLogger(String value) {
        if(logger == null) {
            logger = new NaiveThreadLogger(value);
        }
        return logger;
    }
}
