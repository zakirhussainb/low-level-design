package main.java.design_patterns.creational.factory_pattern.vehicle;

public class Bus implements Vehicle {
    @Override
    public Vehicle getVehicle() {
        System.out.println("You will get a Bus");
        return new Bus();
    }
}
