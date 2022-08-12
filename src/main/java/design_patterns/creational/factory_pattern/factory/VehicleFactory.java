package main.java.design_patterns.creational.factory_pattern.factory;

import main.java.design_patterns.creational.factory_pattern.vehicle.*;

public class VehicleFactory {
    public static Vehicle getVehicle(String vehicleType) {
        switch (vehicleType) {
            case "Car" :
                return new Car();
            case "Bus" :
                return new Bus();
            case "Bike" :
                return new Bike();
            case "Lorry" :
                return new Lorry();
        }
        return new Car();
    }
}
