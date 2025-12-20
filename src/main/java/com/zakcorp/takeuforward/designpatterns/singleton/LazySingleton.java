package com.zakcorp.takeuforward.designpatterns.singleton;

/*
 Lazy Loading (On-Demand Initialization)
In Lazy Loading, the Singleton instance is created only when it's needed
 — the first time the getInstance() method is called.

Real-World Analogy: Coffee Machine
Imagine a coffee machine that only brews coffee when you press the button.
It doesn't waste energy or resources until you actually want a cup.
Similarly, lazy loading creates the Singleton instance only when it's requested.
 */
public class LazySingleton {
    // Object declaration only
    private static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        // only if the object is not created, create a new object.
        if(instance == null) {
            instance = new LazySingleton();
        }
        // otherwise return the already created object.
        return instance;
    }
}

/*
Understanding
    - The instance starts as null.
    - It is only created when getInstance() is first called.
    - Future calls return the already created instance.

Pros
    - Saves memory if the instance is never used.
    - Object creation is deferred until required.

Cons
    - Lazy Loading is Not thread-safe by default.
    Thus, it requires synchronization in multi-threaded environments.

 */
