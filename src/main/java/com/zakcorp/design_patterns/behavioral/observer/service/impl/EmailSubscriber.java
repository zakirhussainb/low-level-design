package com.zakcorp.design_patterns.behavioral.observer.service.impl;

import com.zakcorp.design_patterns.behavioral.observer.dao.Subscriber;
import com.zakcorp.design_patterns.behavioral.observer.service.ISubscriber;

public class EmailSubscriber implements ISubscriber {

    private final Subscriber subscriber;

    public EmailSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Email to " + subscriber.getEmail() + " has been sent with the message " + message);
    }
}
