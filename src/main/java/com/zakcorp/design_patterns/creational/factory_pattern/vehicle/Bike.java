package com.zakcorp.design_patterns.creational.factory_pattern.vehicle;

public class Bike implements Vehicle {
    @Override
    public Vehicle getVehicle() {
        System.out.println("You will get a Bike");
        return new Bike();
    }
}
