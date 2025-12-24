package com.zakcorp.takeuforward.designpatterns.structural;

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
class RazorPayAPI {
    public void makePayment(String invoiceId, double amount) {
        System.out.println("Paid Rs. " + amount + " using Razorpay for invoice: " + invoiceId);
    }
}

// 4. Adapter Class (The Bridge)
class RazorPayAdapter implements PaymentGateway {
    private final RazorPayAPI razorPayAPI;

    public RazorPayAdapter() {
        this.razorPayAPI = new RazorPayAPI();
    }
    // Translates the pay() call to RazorpayAPI's makePayment() method
    @Override
    public void pay(String orderId, double amount) {
        razorPayAPI.makePayment(orderId, amount);
    }
}

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
public class AdapterPattern {
    public static void main(String[] args) {
        // Scenario 1: Using a standard compatible gateway
        CheckoutService payUService = new CheckoutService(new PayUGateway());
        payUService.checkout("123", 456.98);

        // Scenario 2: Using the Incompatible gateway via Adapter
        // The Client code (.checkout) remains exactly the same!
        CheckoutService razorPayService = new CheckoutService(new RazorPayAdapter());
        razorPayService.checkout("456", 567.12);
    }
}
