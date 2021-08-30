package Models;

import Factory.TopicFactory;
import lombok.Getter;
import lombok.NonNull;

public class Publisher {
    @Getter
    private final String publisherId;
    private final TopicFactory topicFactory;

    public Publisher(String publisherId, TopicFactory topicFactory) {
        this.publisherId = publisherId;
        this.topicFactory = topicFactory;
    }

    public boolean publishMessage(@NonNull String topicName, @NonNull String message){
        Topic topic =  this.topicFactory.getTopic(topicName);
        if(topic == null){
            this.topicFactory.createTopic(topicName);
        }
        topic =  this.topicFactory.getTopic(topicName);
        if(topic != null){
            return topic.publishMessage(message);
        }
        return false;
    }
}
