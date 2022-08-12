package main.java.design_patterns.creational.singleton.naive;

public class NaiveDemo {
    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        Logger logger = Logger.getLogger("FOO");
        Logger anotherLogger = Logger.getLogger("BAR");
        System.out.println(logger.value);
        System.out.println(anotherLogger.value);
    }
}
