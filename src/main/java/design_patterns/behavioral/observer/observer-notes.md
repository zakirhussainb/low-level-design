# Observer Design Pattern

### What is Observer ?

It is a behavioural design pattern that lets you define a subscription mechanism to notify multiple objects about
any events that happen to the object they are observing.

### Why do we need Observer Design Pattern ?

* Imagine that you have two types of objects: a Customer and a Store. 
The customer is very interested in a particular brand of product (say, it’s a new model of the iPhone) which should 
become available in the store very soon.
* The customer could visit the store every day and check product availability. But while the product is still en route, 
most of these trips would be pointless.
* On the other hand, the store could ask the customer to subscribe to their notification emails where it can notify them
whenever the product is ready at their store. This would save the subscribed customers from endless trips to the store.

### How to create Observer pattern ?
1. There must be a Subject where you must,
   1. Collect a bunch of observers
   2. Register an observer or a List of observers
   3. Unregister an observer
   4. Notify an observer
      1. When this method is called, it must go through the list of observers one-by-one and send messages to them.
2. There must be an Observer interface -> with a sendMessage() method
   1. ConcreteObserver A -> The EmailSubscriber sends a message to corresponding emailID once it receives a message 
   from the publisher manager.
   2. ConcreteObserver B -> The MobileSubscriber sends a message to corresponding mobile no. once it receives a message
   from the publisher manager.

   ![Alt text](./resources/observer.png?raw=true)

   Image representing the above pattern

### References
1. https://youtu.be/gbTWHeGUeXs
2. https://refactoring.guru/design-patterns/observer