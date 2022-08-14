# Singleton Design Pattern

### What is Singleton ?
* Singleton is a creational design pattern, which ensures that only one object of its kind exists and provides a 
single point of access to it for any other code.

### Why do we need Singleton Design Pattern ?
1. Most common reason for this is to control access to some shared resource, for example database connections, logging, 
caching, thread pools, configuration settings etc.

### How to create a Singleton class ?  
1. Make the constructor private -> It will prevent to instantiate the Singleton class from outside the class.
2. Create a static private member -> This will contain the instance of the class and will get memory only once.
3. Create a static factory method -> This provides a global point of access to the Singleton object and returns 
the instance to the caller.

### Types of Singleton implementation
#### 1. Naive Implementation - Single Threaded
   - Here you have achieved a single instance of a class, Now classes that are outside the Singleton can access only 
   one instance of the Singleton class.
#### 2. Naive Implementation - Multi Threaded
   - In this implementation though you cannot create multiple instances for a single class from outside a class, 
   but still you can create them using multi-threading.
   - When two threads try to access the `getInstance()` method, then two instances of the same class will be created.
#### 3. Thread-Safe Implementation
   - This implementation is based on **Double-Check-Locking(DCL)**, where you are ensuring that the `getSingleton()/getInstance()`
   method always returns the object that has already been initialized without creating a new object.
   - How to implement ?
     - Declare the static private member as volatile.
       - Why volatile ? What is volatile ?
         - The volatile keyword now ensures that multiple threads handle the singleton instance correctly.
         - The volatile keyword does not cache the value of the variable and always reads the variable from the main memory.
     - Use synchronization inside the static factory method
       - Initialize a local variable `localRef` referring to the static private member(logger).
       This seems unnecessary but the effect of this is that in cases where helper is already initialized 
       (i.e., most of the time), the volatile field is only accessed once (due to "return `localRef;`" instead of "return `helper;`"), 
       which can improve the method's overall performance by as much as 40 percent.
       - Obtain a lock in case two threads call `getLogger()` method
         ``` 
         synchronized (ThreadSafeLogger.class) {
           if( instance == null ) {
              instance = new ThreadSafeLogger(value);
           }
           return instance;
         }
         ```
### References
1. https://en.wikipedia.org/wiki/Double-checked_locking#Usage_in_Java
2. https://refactoring.guru/design-patterns/singleton
3. https://youtu.be/oeQWxhlnCHM
4. https://www.javatpoint.com/singleton-design-pattern-in-java
5. https://javarevisited.blogspot.com/2011/03/10-interview-questions-on-singleton.html#axzz7bjQpJtCG
6. https://www.javatpoint.com/volatile-keyword-in-java