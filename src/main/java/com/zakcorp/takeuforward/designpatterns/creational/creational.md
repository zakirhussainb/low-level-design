# Creational Design Patterns

This document covers common Creational Design Patterns used to solve object creation mechanisms in a controlled and standard way.

## Table of Contents

- [1. Singleton Design Pattern](#1-singleton-design-pattern)
    - [Top 10 Points to Remember](#top-10-points-to-remember)
    - [Implementation (Bill Pugh Approach - Best Practice)](#implementation-bill-pugh-approach---best-practice)
    - [Usage (Client Code)](#usage-client-code)
- [2. Factory Design Pattern](#2-factory-design-pattern)
    - [Top 10 Points to Remember](#top-10-points-to-remember-1)
    - [Implementation (Logistics Example)](#implementation-logistics-example)
    - [Usage (Client Code)](#usage-client-code-1)
- [3. Builder Design Pattern](#3-builder-design-pattern)
    - [Top 10 Points to Remember](#top-10-points-to-remember-2)
    - [Implementation (Java)](#implementation-java)
    - [Usage (Client Code)](#usage-client-code-2)

---

## 1. Singleton Design Pattern
### Top 10 Points to Remember

1.  **Definition:** Ensures a class has exactly one instance and provides a global access point to it.
2.  **Key Components:** Requires a **private constructor** (to prevent `new`), a **static variable** (to hold the instance), and a **static factory method** (to return the instance).
3.  **Eager Loading:** Instance is created as soon as the class loads. It is thread-safe but may waste memory if the instance is never used.
4.  **Lazy Loading:** Instance is created only when `getInstance()` is called for the first time. Saves memory but requires care in multi-threaded environments.
5.  **Thread Safety:** Critical in Singleton. Without safeguards, two threads can access `getInstance()` simultaneously and create two different objects.
6.  **Double-Checked Locking:** A technique to ensure thread safety in Lazy Loading by checking for `null` twice (once before locking and once inside the lock) to improve performance.
7.  **Bill Pugh Implementation:** The **best practice** for Java. It uses a private static inner class to hold the instance. It guarantees lazy loading and thread safety without `synchronized` overhead.
8.  **Testing Challenges:** Singletons hold global state, making them **hard to mock** and unit test in isolation.
9.  **SRP Violation:** It acts as an anti-pattern to the **Single Responsibility Principle** (SRP) because the class manages its own lifecycle in addition to its business logic.
10. **Usage:** Best for stateless utilities or shared resources like Loggers, Cache Managers, and Configuration Loaders.

### Implementation (Bill Pugh Approach - Best Practice)

This implementation relies on the Java class loader mechanism to ensure thread safety and lazy loading.

```java
public class LoggerSingleton {

    // 1. Private Constructor prevents instantiation from other classes
    private LoggerSingleton() {
        System.out.println("Logger initialized.");
    }

    // 2. Static Inner Class - distinct from the outer class
    // This is NOT loaded until getInstance() is referenced.
    private static class LoggerHolder {
        private static final LoggerSingleton INSTANCE = new LoggerSingleton();
    }

    // 3. Global Access Point
    public static LoggerSingleton getInstance() {
        return LoggerHolder.INSTANCE;
    }

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}
```

### Usage (Client Code)

```java
public class SingletonDemo {
    public static void main(String[] args) {
        // Both calls return the exact same object instance
        LoggerSingleton logger1 = LoggerSingleton.getInstance();
        LoggerSingleton logger2 = LoggerSingleton.getInstance();

        logger1.log("This is a log message.");

        // Verification
        System.out.println(logger1 == logger2); // Output: true
    }
}
```

---

## 2. Factory Design Pattern
### Top 10 Points to Remember

1.  **Definition:** Defines an interface for creating an object but lets subclasses decide which class to instantiate.
2.  **Loose Coupling:** Removes the dependency between the client code and the concrete classes (e.g., Client doesn't need to know about `AirLogistics` or `RoadLogistics`).
3.  **Open/Closed Principle:** You can add new product types (e.g., `ShipLogistics`) without breaking or modifying existing client code.
4.  **Single Responsibility Principle:** Moves the object creation code to one specific place (the Factory), making the code easier to maintain.
5.  **Runtime Flexibility:** The decision of *what* to create can be determined at runtime based on user input or configuration.
6.  **Encapsulation:** Hides the complex logic of object creation (instantiation, setup, configuration) from the client.
7.  **Code Reusability:** Centralizes object creation logic, preventing code duplication across the application.
8.  **Testability:** Makes it easier to test business logic independently by mocking the interface or the factory.
9.  **Complexity Trade-off:** While it makes code more robust, it introduces extra classes and interfaces which might be overkill for very simple applications.
10. **When to Use:** Use it when you don't know beforehand the exact types and dependencies of the objects your code should work with.

---

### Implementation (Logistics Example)

Refactoring a tight-coupled Logistics system into a Factory pattern.

```java
// 1. Interface (The Product)
interface Logistics {
    void planDelivery();
}

// 2. Concrete Products
class RoadLogistics implements Logistics {
    @Override
    public void planDelivery() {
        System.out.println("Package will be delivered by Truck via Road.");
    }
}

class AirLogistics implements Logistics {
    @Override
    public void planDelivery() {
        System.out.println("Package will be delivered by Airplane via Air.");
    }
}

// 3. The Factory Class
class LogisticsFactory {

    // Static Factory Method
    public static Logistics createLogistics(String transportType) {
        if (transportType.equalsIgnoreCase("ROAD")) {
            return new RoadLogistics();
        } else if (transportType.equalsIgnoreCase("AIR")) {
            return new AirLogistics();
        }
        throw new IllegalArgumentException("Unknown transport type: " + transportType);
    }
}
```

### Usage (Client Code)

```java
public class FactoryPatternDemo {
    public static void main(String[] args) {
        
        // The client doesn't know about 'RoadLogistics' or 'AirLogistics' classes directly.
        // It simply asks the factory for the type it needs.
        
        Logistics transport1 = LogisticsFactory.createLogistics("AIR");
        transport1.planDelivery(); // Output: Package will be delivered by Airplane via Air.

        Logistics transport2 = LogisticsFactory.createLogistics("ROAD");
        transport2.planDelivery(); // Output: Package will be delivered by Truck via Road.
    }
}
```

## 3. Builder Design Pattern

### Top 10 Points to Remember

1.  **Separation of Concerns:** Separates the construction of an object from its final representation.
2.  **Solves Telescoping Constructors:** Eliminates the need for multiple constructors with long, confusing lists of parameters.
3.  **Immutability:** Allows you to build objects step-by-step but still keep the final object **immutable** (using `final` fields).
4.  **Fluent API:** Uses method chaining (methods return `this`) to create readable code (e.g., `.withColor().withGiftWrap()`).
5.  **Null Safety:** Removes the need to pass `null` explicitly for optional parameters you don't need.
6.  **Structure:** Typically requires a **static nested Builder class** and a **private constructor** in the main class.
7.  **Readability:** Drastically improves the readability of client code, minimizing errors related to parameter order.
8.  **Scalability:** It is easy to add new optional parameters later without breaking existing code.
9.  **When to Use:** Ideal for objects with **many optional fields** (4+); usually "overkill" for simple classes with only 1-2 fields.
10. **Real-World Examples:** Heavily used in libraries like **Lombok** (`@Builder`), `java.lang.StringBuilder`, and HTTP Request builders.

### Implementation (Java)

Below is an implementation of a Shopping Cart Item.

```java
public class BuildCartItem {
    
    // 1. Required Components
    private final int quantity;
    private final String paymentOption;

    // 2. Optional Components
    private final String color;
    private final boolean isGiftWrap;
    private final boolean isSaveForLater;
    private final double discountedPrice;

    // 3. Private constructor forces use of the Builder
    private BuildCartItem(ItemBuilder itemBuilder) {
        this.quantity = itemBuilder.quantity;
        this.paymentOption = itemBuilder.paymentOption;
        this.color = itemBuilder.color;
        this.isGiftWrap = itemBuilder.isGiftWrap;
        this.isSaveForLater = itemBuilder.isSaveForLater;
        this.discountedPrice = itemBuilder.discountedPrice;
    }

    // 4. Static Nested Builder Class
    public static class ItemBuilder {
        // Required
        private final int quantity;
        private final String paymentOption;

        // Optional
        private String color;
        private boolean isGiftWrap;
        private boolean isSaveForLater;
        private double discountedPrice;

        // Builder constructor with required fields
        public ItemBuilder(int quantity, String paymentOption) {
            this.quantity = quantity;
            this.paymentOption = paymentOption;
        }

        // Fluent Setters (return 'this')
        public ItemBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        public ItemBuilder withGiftWrap(boolean isGiftWrap) {
            this.isGiftWrap = isGiftWrap;
            return this;
        }

        public ItemBuilder withSaveForLater(boolean isSaveForLater) {
            this.isSaveForLater = isSaveForLater;
            return this;
        }

        public ItemBuilder withDiscountedPrice(double discountedPrice) {
            this.discountedPrice = discountedPrice;
            return this;
        }

        // 5. Build Method to return the final object
        public BuildCartItem build() {
            return new BuildCartItem(this);
        }
    }
}
```

### Usage (Client Code)

```java
public class BuilderPattern {
    public static void main(String[] args) {
        
        // Example 1: Basic object with only required fields
        BuildCartItem footBall = new BuildCartItem.ItemBuilder(1, "COD")
                .build();

        // Example 2: Complex object with specific options
        // Notice how readable this is compared to a constructor
        BuildCartItem bat = new BuildCartItem.ItemBuilder(1, "Online")
                .withColor("white")
                .withGiftWrap(true)
                .build();
    }
}
```
