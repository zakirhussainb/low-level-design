package com.zakcorp.takeuforward.designpatterns.singleton;

/*
Eager Loading (Early Initialization)
In Eager Loading, the Singleton instance is created as soon as the class is loaded,
regardless of whether it's ever used. Let's understand this with a real-life analogy.

Real-World Analogy: Fire Extinguisher in a Building
A fire extinguisher is always present, even if a fire never occurs. Similarly,
eager loading creates the Singleton instance upfront, just in case it's needed.
 */
public class EagerSingleton {
    // Object declaration and initialization
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {} // prevents the creation of object using new keyword

    public static EagerSingleton getInstance() {
        return instance;
    }
}

/*
Understanding
    The object is created immediately when the class is loaded.
    It's always available and inherently thread-safe.

Pros
    Very simple to implement.
    Thread-safe without any extra handling.

Cons
    Wastes memory if the instance is never used.
    Not suitable for heavy objects.
 */