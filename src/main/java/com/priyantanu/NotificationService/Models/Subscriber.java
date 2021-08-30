package com.priyantanu.NotificationService.Models;


import com.priyantanu.NotificationService.Factory.TopicFactory;
import lombok.Getter;
import lombok.NonNull;

public class Subscriber {
    @Getter
    private final String subscriberId;
    private final TopicFactory topicFactory;

    public Subscriber(@NonNull String subscriberId, @NonNull TopicFactory topicFactory){
        this.subscriberId = subscriberId;
        this.topicFactory = topicFactory;
    }

    public boolean areNewMessagesPresent(@NonNull String topicName){
            Topic topic = this.topicFactory.getTopic(topicName);
            return topic.areNewMessagesPresent(this.subscriberId);
    }

    public String getNextMessage(@NonNull String topicName){
        Topic topic = this.topicFactory.getTopic(topicName);
        return topic.getNextMessage(this.subscriberId);
    }

    public boolean clearSubscriberOffset(@NonNull String topicName){
        Topic topic = this.topicFactory.getTopic(topicName);
        return topic.clearSubscriberOffset(this.subscriberId);
    }
}
