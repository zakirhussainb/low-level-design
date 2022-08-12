package main.java.design_patterns.creational.singleton.naive;

// Logger with a Singleton Design Pattern
public class Logger {
    // This member will get memory once, since it is a static member, it will contain the instance of the class
    private static Logger logger;
    public String value; // This is just for example

    /*
    Making the constructor private -> It will prevent to instantiate the Singleton class from outside the class.
     */
    private Logger(String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    /* Create a static factory method -> This provides a global point of access to the Singleton Object and returns the
    instance to the caller */
    public static Logger getLogger(String value) {
        if(logger == null) {
            logger = new Logger(value);
        }
        return logger;
    }
}
