package main.java.design_patterns.creational.singleton.naivethread;

import static main.java.design_patterns.creational.singleton.Constants.CONDITION_FOR_SINGLETON;

/**
 * Here in this demo you will see the output that there two singletons being created.
 * That is because we have not handled our NaiveThreadLogger to be threadsafe.
 */
public class NaiveThreadDemo {
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
            NaiveThreadLogger logger = NaiveThreadLogger.getLogger("This is FOO");
            System.out.println(logger.value);
        }
    }

    static class ThreadBar implements Runnable {

        @Override
        public void run() {
            NaiveThreadLogger logger = NaiveThreadLogger.getLogger("This is BAR");
            System.out.println(logger.value);
        }
    }
}
