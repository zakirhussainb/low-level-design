package main.java.design_patterns.creational.singleton;

// This is a Logger with a Singleton Design Pattern
public class Logger {
    private static int counter = 0; // This is just for example
    // This member will get memory once since it is static, it contains the instance of the class
    private static Logger logger;

    /*
    Making the constructor private -> It will prevent to instantiate the Singleton class from outside the class.
     */
    private Logger() {
        System.out.println("New instance is created " + counter++);
    }

    /* Create a static factory method -> This provides a global point of access to the Singleton Object and returns the
    instance to the caller */
    public static Logger getLogger() {
        if(logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    /*
    This method is also for example purposes.
     */
    public void log(String message) {
        System.out.println(message);
    }
}
