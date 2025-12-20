package com.zakcorp.takeuforward.designpatterns.factory;

/*
Definition:

Rather than calling a constructor directly to create an object,
we use a factory method to create that object based on some input or condition.

When can the Factory Pattern be used?:
    - The client code needs to work with multiple types of objects.
    - The decision of which class to instantiate must be made at runtime.
    - The instantiation process is complex or needs to be controlled.
 */

// Real-life Product Example - building a logistics application that needs to handle
// different types of transport services: By Road, By Air, etc

// Bad Practice: The object creation logic is tightly coupled with business logic

interface Logistics {
    void send();
}

class Road implements Logistics {
    @Override
    public void send() {
        System.out.println("Sending by road logic");
    }
}

class Air implements Logistics {
    @Override
    public void send() {
        System.out.println("Sending by air logic");
    }
}

class BadLogisticsService {
    public void send(String mode) {
        if(mode.equals("Air")) {
            Logistics logistics = new Air();
            logistics.send();
        } else if(mode.equals("Road")) {
            Logistics logistics = new Road();
            logistics.send();
        }
    }
}
class LogisticsFactory {
    public static Logistics getLogistics(String mode) {
        if(mode.equals("Air")) {
            return new Air();
        } else if(mode.equals("Road")) {
            return new Road();
        }
        throw new IllegalArgumentException("Unknown transportation mode is provided");
    }
}
class LogisticsService {
    public void send(String mode) {
        /* Using the Logistics Factory to get the desired object based on the mode */
        Logistics logistics = LogisticsFactory.getLogistics(mode);
        logistics.send();
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        // Bad Practice
        BadLogisticsService badLogisticsService = new BadLogisticsService();
        badLogisticsService.send("Air");
        badLogisticsService.send("Road");

        LogisticsService logisticsService = new LogisticsService();
        logisticsService.send("Air");
        logisticsService.send("Road");
    }
}
