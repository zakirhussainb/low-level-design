package com.zakcorp.design_patterns.creational.singleton.naive;

import static com.zakcorp.design_patterns.creational.singleton.Constants.CONDITION_FOR_SINGLETON;

public class NaiveDemo {
    public static void main(String[] args) {
        System.out.println(CONDITION_FOR_SINGLETON);
        Logger logger = Logger.getLogger("FOO");
        Logger anotherLogger = Logger.getLogger("BAR");
        System.out.println(logger.value);
        System.out.println(anotherLogger.value);
    }
}
