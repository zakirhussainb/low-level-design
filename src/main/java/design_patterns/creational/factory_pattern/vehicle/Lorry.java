package main.java.design_patterns.creational.factory_pattern.vehicle;

public class Lorry implements Vehicle {
    @Override
    public Vehicle getVehicle() {
        System.out.println("You will get a Lorry");
        return new Lorry();
    }
}
