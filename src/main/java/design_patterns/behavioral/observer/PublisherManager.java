package main.java.design_patterns.behavioral.observer;

import main.java.design_patterns.behavioral.observer.service.ISubscriber;

import java.util.*;

public class PublisherManager {
    List<ISubscriber> list;
    public PublisherManager() {
        list = new ArrayList<>();
    }

    public void subscribe(ISubscriber subscriber) {
        list.add(subscriber);
    }

    public void subscribe(List<ISubscriber> subscriberList) {
        list.addAll(subscriberList);
    }

    public void unsubscribe(ISubscriber subscriber) {
        list.remove(subscriber);
    }

    public void publish(String message) {
        for(ISubscriber subscriber : list) {
            subscriber.publishMessage(message);
        }
    }
}
