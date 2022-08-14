package com.zakcorp.design_patterns.creational.singleton.naive;

import org.junit.Test;

import static com.zakcorp.design_patterns.creational.singleton.Constants.CONDITION_FOR_SINGLETON;
import static org.junit.Assert.assertEquals;

public class NaiveLoggerTest {

    @Test
    public void testGetLogger() {
        System.out.println(CONDITION_FOR_SINGLETON);
        Logger logger = Logger.getLogger("FOO");
        Logger anotherLogger = Logger.getLogger("BAR");
        System.out.println(logger.value);
        System.out.println(anotherLogger.value);
        assertEquals(logger.value, anotherLogger.value);
    }
}
