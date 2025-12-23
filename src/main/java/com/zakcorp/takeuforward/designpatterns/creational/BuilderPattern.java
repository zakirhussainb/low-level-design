package com.zakcorp.takeuforward.designpatterns.creational;

/*
Top 10 Points to Remember
Definition: Separates the construction of a complex object from its representation, allowing the same process to create different configurations.

Solves Telescoping Constructors: Eliminates the "Telescoping Constructor Anti-Pattern" (multiple constructors with long, confusing lists of parameters).

Immutability: Allows you to build objects step-by-step but still keep the final object immutable (using final fields).

Fluent API: Uses method chaining (methods return this) to create readable, English-like code (e.g., .withCheese().withDrink()).

Null Safety: Removes the need to pass null explicitly for optional parameters you don't need.

Implementation Structure: Typically requires a static nested Builder class and a private constructor in the main class.

Readability: drastically improves the readability of object creation code, minimizing errors related to parameter order.

Scalability: Easy to add new optional parameters later without breaking existing code or creating new constructor overloads.

When to Use: Ideal for objects with many optional fields (4+); usually "overkill" for simple classes with only 1-2 fields.

Real-World Examples: Heavily used in libraries like Lombok (@Builder) and complex systems like HTTP Request builders or Shopping Cart configurations.

 */
class BuildCartItem {
    // Required Components
    private final int quantity;
    private final String paymentOption;

    // Optional components
    private final String color;
    private final boolean isGiftWrap;
    private final boolean isSaveForLater;
    private final double discountedPrice;

    private BuildCartItem(ItemBuilder itemBuilder) {
        this.quantity = itemBuilder.quantity;
        this.paymentOption = itemBuilder.paymentOption;
        this.color = itemBuilder.color;
        this.isGiftWrap = itemBuilder.isGiftWrap;
        this.isSaveForLater = itemBuilder.isSaveForLater;
        this.discountedPrice = itemBuilder.discountedPrice;
    }

    public static class ItemBuilder {
        // Required Components
        private final int quantity;
        private final String paymentOption;

        // Optional components
        private String color;
        private boolean isGiftWrap;
        private boolean isSaveForLater;
        private double discountedPrice;

        // Build constructor with required fields
        public ItemBuilder(int quantity, String paymentOption) {
            this.quantity = quantity;
            this.paymentOption = paymentOption;
        }

        // Method to set color
        public ItemBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        // Method to set isGiftWrap
        public ItemBuilder withGiftWrap(boolean isGiftWrap) {
            this.isGiftWrap = isGiftWrap;
            return this;
        }

        // Method to set isSaveForLater
        public ItemBuilder withSaveForLater(boolean isSaveForLater) {
            this.isSaveForLater = isSaveForLater;
            return this;
        }

        // Method to set discountedPrice
        public ItemBuilder withDiscountedPrice(double discountedPrice) {
            this.discountedPrice = discountedPrice;
            return this;
        }

        public BuildCartItem build() {
            return new BuildCartItem(this);
        }
    }
}
public class BuilderPattern {
    public static void main(String[] args) {
        BuildCartItem footBall = new BuildCartItem.ItemBuilder(1, "COD")
                .build();

        BuildCartItem bat = new BuildCartItem.ItemBuilder(1, "Online")
                .withColor("white")
                .withGiftWrap(true)
                .build();
    }
}
