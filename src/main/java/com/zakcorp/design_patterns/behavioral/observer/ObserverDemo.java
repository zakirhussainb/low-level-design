package com.zakcorp.design_patterns.behavioral.observer;

import com.zakcorp.design_patterns.behavioral.observer.dao.Subscriber;
import com.zakcorp.design_patterns.behavioral.observer.service.ISubscriber;
import com.zakcorp.design_patterns.behavioral.observer.service.impl.EmailSubscriber;
import com.zakcorp.design_patterns.behavioral.observer.service.impl.MobileSubscriber;

import java.util.ArrayList;
import java.util.List;

public class ObserverDemo {
    public static void main(String[] args) {
        List<ISubscriber> list = new ArrayList<>();
        ISubscriber emailSubscriber1 = new EmailSubscriber(new Subscriber("A", 1, "andrew@email.com", ""));
        ISubscriber emailSubscriber2 = new EmailSubscriber(new Subscriber("B", 2, "belford@email.com", ""));
        ISubscriber emailSubscriber3 = new EmailSubscriber(new Subscriber("C", 3, "cedric@email.com", ""));

        ISubscriber mobileSubscriber1 = new MobileSubscriber(new Subscriber("D", 5, "dogma@email.com", "123456789"));
        ISubscriber mobileSubscriber2 = new MobileSubscriber(new Subscriber("E", 6, "eccentric@email.com", "987654321"));
        ISubscriber mobileSubscriber3 = new MobileSubscriber(new Subscriber("F", 7, "fish@email.com", "987612567"));

        list.add(emailSubscriber1); list.add(emailSubscriber2); list.add(emailSubscriber3);

        list.add(mobileSubscriber1); list.add(mobileSubscriber2); list.add(mobileSubscriber3);

        PublisherManager manager = new PublisherManager();
        manager.subscribe(list);

        manager.publish("\"Hello Everyone you are receiving this message because you have subscribed...\"");

        manager.unsubscribe(mobileSubscriber1);

        manager.publish("\"Hello Everyone you are receiving this message because you have subscribed...\"");

    }
}
