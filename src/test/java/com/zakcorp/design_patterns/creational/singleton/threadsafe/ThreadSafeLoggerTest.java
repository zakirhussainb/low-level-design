package com.zakcorp.design_patterns.creational.singleton.threadsafe;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.zakcorp.design_patterns.creational.singleton.Constants.CONDITION_FOR_SINGLETON;
import static org.junit.Assert.assertEquals;

public class ThreadSafeLoggerTest {

    @Test
    public void testGetLogger() throws InterruptedException {
        System.out.println(CONDITION_FOR_SINGLETON);
        int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        String message;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < numberOfThreads; i++) {
            if (i == 0)
                message = "This is BAR";
            else
                message = "This is FOO";
            String finalMessage = message;
            service.submit(() -> {
                ThreadSafeLogger logger = ThreadSafeLogger.getLogger(finalMessage);
                latch.countDown();
                set.add(logger.value);
                System.out.println(logger.value);
            });
        }
        latch.await();
        assertEquals(1, set.size()); // The set size will be one, because only one singleton is created.
    }
}
