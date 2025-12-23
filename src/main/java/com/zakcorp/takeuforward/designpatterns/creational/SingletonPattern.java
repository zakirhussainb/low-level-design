package com.zakcorp.takeuforward.designpatterns.creational;

/*
In Eager Loading, the Singleton instance is created as soon as the class is loaded,
regardless of whether it's ever used. Let's understand this with a real-life analogy.

Real-World Analogy: Fire Extinguisher in a Building
A fire extinguisher is always present, even if a fire never occurs. Similarly, eager loading creates
the Singleton instance upfront, just in case it's needed.
 */
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {}

    public EagerSingleton getInstance() {
        return instance;
    }
}

/*
In Lazy Loading, the Singleton instance is created only when it's needed — the first time the getInstance() method is called.

Real-World Analogy: Coffee Machine
Imagine a coffee machine that only brews coffee when you press the button. It doesn't waste energy or
resources until you actually want a cup. Similarly, lazy loading creates the Singleton instance
 only when it's requested.
 */
class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {}

    public LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
/*
Synchronized keyword:

This is the simplest way to ensure thread safety. By synchronizing the method that creates the instance,
we can prevent multiple threads from creating separate instances at the same time.
However, this approach can lead to performance issues due to the overhead of synchronization.
 */
class SynchronizedSingleton {
    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {}

    // The synchronized keyword ensures that only one thread at a time can execute
    // the getInstance() method. This prevents multiple threads from entering the
    // method simultaneously and creating multiple instances.
    public static synchronized SynchronizedSingleton getInstance() {
        if(instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }
}

/*
Double-Checked Locking:

    - This is a more efficient way to achieve thread safety.
    - The idea is to check if the instance is null before acquiring the lock.
    - If it is, then we synchronize the block and check again.
    - This reduces the overhead of synchronization after the instance has been created.
 */

class DoubleCheckedLockingSingleton {
    /* volatile keyword ensures changes made by one thread are visible to others.
    Without volatile, one thread might create the Singleton instance,
    but other threads may not see the updated value due to caching.
    volatile ensures that the instance is always read from the main memory,
    so all threads see the most up-to-date version.
     */
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {}

    public static DoubleCheckedLockingSingleton getInstance() {
        if(instance != null) return instance;
        synchronized (DoubleCheckedLockingSingleton.class) {
            if (instance == null) {
                instance = new DoubleCheckedLockingSingleton();
            }
            return instance;
        }
    }
}

// Best practice for Lazy loading
/*
This is a highly efficient way to implement the Singleton pattern.
It uses a static inner helper class to hold the Singleton instance.
The instance is created only when the inner class is loaded, which happens only when
getInstance() is called for the first time.
*/
class BillPughSingleton {
    private BillPughSingleton() {}

    private static class Holder {
        private static final BillPughSingleton instance = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return Holder.instance;
    }
}
public class SingletonPattern {
}
