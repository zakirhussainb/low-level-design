package main.java.design_patterns.creational.factory_pattern.vehicle;

public class Car implements Vehicle {
    @Override
    public Vehicle getVehicle() {
        System.out.println("You will get a Car");
        return new Car();
    }
}
