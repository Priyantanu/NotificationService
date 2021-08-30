package com.priyantanu.NotificationService.Factory;

import com.priyantanu.NotificationService.Models.Subscriber;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SubscriberFactory {
    private final List<Subscriber> subscriberList;
    private final TopicFactory topicFactory;

    public SubscriberFactory(@NonNull TopicFactory topicFactory) {
        this.subscriberList = new ArrayList<>();
        this.topicFactory = topicFactory;
    }

    public Subscriber getSubscriber(@NonNull String subscriberId){
        Subscriber subscriberResponse;
        for(Subscriber subscriber: subscriberList){
            if(subscriber.getSubscriberId().equals(subscriberId))
                return subscriber;
        }
        subscriberResponse = new Subscriber(subscriberId, topicFactory);
        subscriberList.add(subscriberResponse);
        return subscriberResponse;
    }
}
