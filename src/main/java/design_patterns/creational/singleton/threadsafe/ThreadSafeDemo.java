package main.java.design_patterns.creational.singleton.threadsafe;

import static main.java.design_patterns.creational.singleton.Constants.CONDITION_FOR_SINGLETON;

/**
 * Here in this demo you will see in the output, only one singleton being created.
 * We have handled our ThreadSafeLogger with Double-Checked-Locking(DCL)
 */
public class ThreadSafeDemo {
    public static void main(String[] args) {
        System.out.println(CONDITION_FOR_SINGLETON);
        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();
    }

    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            ThreadSafeLogger logger = ThreadSafeLogger.getLogger("FOO");
            System.out.println(logger.value);
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            ThreadSafeLogger logger = ThreadSafeLogger.getLogger("BAR");
            System.out.println(logger.value);
        }
    }
}
