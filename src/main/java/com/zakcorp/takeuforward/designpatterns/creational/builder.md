# Builder Design Pattern

**Type:** Creational Design Pattern

## 🧐 Theory

The **Builder Pattern** is a creational design pattern that separates the construction of a complex object from its representation. This allows you to create different types and representations of an object using the same construction process.

### Formal Definition
> "Builder pattern builds a complex object step by step. It separates the construction of a complex object from its representation, so that the same construction process can create different representations."

### 🍕 Real-life Analogy (Custom Pizza)
Imagine ordering a pizza. You don't just say "Give me a pizza." You select the crust, then the sauce, the cheese, and finally the toppings. The chef (the Builder) follows your instructions step-by-step to construct the final product. Different customers can use the same process to get entirely different pizzas.

---

## 🛑 Understanding the Problem
### The Telescoping Constructor Anti-Pattern

Imagine you are building a `BurgerMeal`. It has mandatory items (Bun, Patty) and optional items (Cheese, Sides, Toppings).

Without a Builder, you might try to solve this with **Constructor Overloading**:

```java
// Hard to read, easy to mix up parameters
public BurgerMeal(String bun, String patty) { ... }
public BurgerMeal(String bun, String patty, boolean cheese) { ... }
public BurgerMeal(String bun, String patty, boolean cheese, String side) { ... }
```

**Issues:**
1.  **Hard to Read:** `new BurgerMeal("wheat", "veg", null, null, false)` — what do those nulls mean?
2.  **Error Prone:** It's easy to swap parameters of the same type.
3.  **Inflexible:** If you want a burger with just a drink but no cheese, you still have to deal with the cheese parameter.

---

## 🚀 The Solution: Builder Pattern

The Builder Pattern solves this by using a static inner class to build the object step-by-step. It provides a readable, fluent interface to set only the parameters you need.

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

---

## 💻 Implementation (Java)

Below is an implementation of a Shopping Cart Item.

```java
package com.zakcorp.takeuforward.designpatterns.creational;

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

---

## ⚖️ Pros and Cons

| Pros | Cons |
| :--- | :--- |
| **Readable Client Code:** Easy to understand what is being set. | **Boilerplate:** Requires creating a static inner class and duplicating fields. |
| **Immutability:** Objects can be made immutable easily. | **Complexity:** Overkill for classes with few parameters (1-3). |
| **Flexibility:** Easy to add new parameters without breaking legacy code. | **Memory:** Creates two objects (Builder + Product) instead of one. |
| **Safety:** Eliminates `NullPointerExceptions` from passing explicit nulls. | |

---

## 🌍 Real World Examples

1.  **Lombok `@Builder`:** Automatically generates the builder code above with a single annotation.
2.  **`java.lang.StringBuilder`:** Used to build Strings step-by-step.
3.  **Amazon/E-commerce:** Building a complex order object (Shipping, Billing, Gift Wrap, Promos).