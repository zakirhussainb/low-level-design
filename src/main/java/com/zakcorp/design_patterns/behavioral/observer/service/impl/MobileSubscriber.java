package com.zakcorp.design_patterns.behavioral.observer.service.impl;

import com.zakcorp.design_patterns.behavioral.observer.dao.Subscriber;
import com.zakcorp.design_patterns.behavioral.observer.service.ISubscriber;

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
