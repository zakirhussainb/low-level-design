# Structural Design Patterns

Structural design patterns are concerned with the **composition of classes and objects**. They focus on how to assemble classes and objects into larger structures while keeping these structures flexible and efficient.

## Table of Contents

- [1. Adapter Design Pattern](#1-adapter-design-pattern)
    - [Top 10 Points to Remember](#top-10-points-to-remember)
    - [Implementation (Payment Gateway Example)](#implementation-payment-gateway-example)
    - [Usage (Client Code)](#usage-client-code)
- [2. Decorator Design Pattern](#2-decorator-design-pattern)
    - [Top 10 Points to Remember](#top-10-points-to-remember-1)
    - [Implementation](#implementation)
    - [Usage (Client Code)](#usage-client-code-1)
- [3. Facade Design Pattern](#3-facade-design-pattern)
    - [Top 10 Points to Remember](#top-10-points-to-remember-2)
    - [Implementation](#implementation-1)
    - [Usage (Client Code)](#usage-client-code-2)
- [4. Composite Design Pattern](#4-composite-design-pattern)
    - [Top 10 Points to Remember](#top-10-points-to-remember-3)
    - [Implementation](#implementation-2)
    - [Usage (Client Code)](#usage-client-code-3)
- [5. Proxy Design Pattern](#5-proxy-design-pattern)
    - [Top 10 Points to Remember](#top-10-points-to-remember-4)
    - [Implementation](#implementation-3)
    - [Usage (Client Code)](#usage-client-code-4)
- [6. Bridge Design Pattern](#6-bridge-design-pattern)
    - [Top 10 Points to Remember](#top-10-points-to-remember-5)
    - [Implementation](#implementation-4)
    - [Usage (Client Code)](#usage-client-code-5)
- [7. Flyweight Design Pattern](#7-flyweight-design-pattern)
    - [Top 10 Points to Remember](#top-10-points-to-remember-6)
    - [Implementation](#implementation-5)
    - [Usage (Client Code)](#usage-client-code-6)

---

## 1. Adapter Design Pattern

### Top 10 Points to Remember
1.  **Definition:** Acts as a bridge to let classes with incompatible interfaces work together.
2.  **Also Known As:** Often referred to as a **Wrapper** pattern.
3.  **Key Goal:** Converts the interface of a class into another interface the client expects.
4.  **Core Components:**
  * **Target:** The interface the client expects (e.g., `PaymentGateway`).
  * **Adaptee:** The existing incompatible class (e.g., `RazorpayAPI`).
  * **Adapter:** The class that implements the Target and wraps the Adaptee.
5.  **Reusability:** Allows reusability of existing legacy classes or third-party libraries without modifying their source code.
6.  **Decoupling:** The client code remains coupled only to the Interface, not the specific implementation or the incompatible class.
7.  **When to Use:** Essential when integrating 3rd-party libraries (like Payment Gateways) where you cannot change their API to match your system.
8.  **Principle:** Helps adhere to the **Open/Closed Principle** (you can add new adapters without changing client code).
9.  **Trade-off:** Adds complexity by introducing an extra layer of abstraction (the adapter class).
10. **Real-World Usage:** Payment Gateway integrations (unifying PayPal, Stripe, Razorpay), Logging frameworks (SLF4J adapters), and Java's `Arrays.asList()` (adapts an Array to a List interface).

### Implementation (Payment Gateway Example)

We have a standard `PaymentGateway` interface. `PayU` follows it, but `Razorpay` has a completely different method signature (`makePayment` instead of `pay`). We use an adapter to fix this.

```java
// 1. Target Interface (Expected by Client)
interface PaymentGateway {
    void pay(String orderId, double amount);
}

// 2. Concrete Implementation (Already Compatible)
class PayUGateway implements PaymentGateway {
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Paid Rs. " + amount + " using PayU for order: " + orderId);
    }
}

// 3. Adaptee (Incompatible Interface)
class RazorpayAPI {
    public void makePayment(String invoiceId, double amountInRupees) {
        System.out.println("Paid Rs. " + amountInRupees + " using Razorpay for invoice: " + invoiceId);
    }
}

// 4. Adapter Class (The Bridge)
class RazorpayAdapter implements PaymentGateway {
    
    private RazorpayAPI razorpayAPI;
    
    public RazorpayAdapter() {
        // In real apps, this might be injected
        this.razorpayAPI = new RazorpayAPI();
    }
    
    // Translates the pay() call to RazorpayAPI's makePayment() method
    @Override
    public void pay(String orderId, double amount) {
        razorpayAPI.makePayment(orderId, amount); 
    }
}
```

### Usage (Client Code)

The CheckoutService (Client) does not need to know that Razorpay works differently. It treats all gateways the same.

```java
class CheckoutService {
    private PaymentGateway paymentGateway;

    // Constructor Injection (Dependency Inversion)
    public CheckoutService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void checkout(String orderId, double amount) {
        paymentGateway.pay(orderId, amount);
    }
}

public class AdapterPatternDemo {
    public static void main(String[] args) {
        
        // Scenario 1: Using a standard compatible gateway
        CheckoutService payUService = new CheckoutService(new PayUGateway());
        payUService.checkout("ORD_123", 1500);
        
        // Scenario 2: Using the Incompatible gateway via Adapter
        // The Client code (.checkout) remains exactly the same!
        CheckoutService razorpayService = new CheckoutService(new RazorpayAdapter());
        razorpayService.checkout("ORD_456", 2000);
    }
}
```