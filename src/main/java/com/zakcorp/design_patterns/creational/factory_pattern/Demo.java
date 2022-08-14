package com.zakcorp.design_patterns.creational.factory_pattern;

import com.zakcorp.design_patterns.creational.factory_pattern.factory.VehicleFactory;
import com.zakcorp.design_patterns.creational.factory_pattern.vehicle.Vehicle;

import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        System.out.println("Enter the type of Vehicle");
        Scanner sc = new Scanner(System.in);
        String vehicleType = sc.next();
        Vehicle vehicle = VehicleFactory.getVehicle( vehicleType );
        vehicle.getVehicle();
    }
}
