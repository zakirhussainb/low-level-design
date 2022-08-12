package main.java.design_patterns.creational.singleton;

public class ClientDemo {
    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            Logger loggerInst = Logger.getLogger();
            loggerInst.log("Message is from User " + i);
        }
    }
}
