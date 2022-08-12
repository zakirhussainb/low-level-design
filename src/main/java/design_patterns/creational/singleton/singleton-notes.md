Naive Singleton

This is basically a singleton which will fail in a multi-threaded environment

Why do we need Singleton Design Pattern ?
1. Most common reason for this is to control access to some shared resource, for example database connections, logging, 
caching, thread pools, configuration settings etc.

How to create a Singleton class ?  
1. Make the constructor private -> It will prevent to instantiate the Singleton class from outside the class.
2. Create a static private member -> This will contain the instance of the class and will get memory only once.
3. Create a static factory method -> This provides a global point of access to the Singleton object and returns 
the instance to the caller.

Types of Singleton implementation
1. Naive Implementation - Single Threaded
2. Naive Implementation - Multi Threaded
   - In this implementation though you cannot create multiple instances for a single class from outside a class, 
   but still you can create them using multi-threading.
   - This is demonstrated in the 
3. Thread-Safe Implementation
