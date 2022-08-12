package main.java.design_patterns.behavioral.observer.service.impl;

import main.java.design_patterns.behavioral.observer.dao.Subscriber;
import main.java.design_patterns.behavioral.observer.service.ISubscriber;

public class MobileSubscriber implements ISubscriber {

    private final Subscriber subscriber;

    public MobileSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Mobile SMS to " + subscriber.getPhone() + " has been sent with the message " + message);
    }
}
