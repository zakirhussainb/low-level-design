package main.java.design_patterns.behavioral.observer.service.impl;

import main.java.design_patterns.behavioral.observer.dao.Subscriber;
import main.java.design_patterns.behavioral.observer.service.ISubscriber;

public class EmailSubscriber implements ISubscriber {

    private final Subscriber subscriber;

    public EmailSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void publishMessage(String message) {
        System.out.println("Email to " + subscriber.getEmail() + " has been sent with the message " + message);
    }
}
