package Resources;

import Factory.PublisherFactory;
import Factory.SubscriberFactory;
import Factory.TopicFactory;
import Models.Publisher;
import Models.Subscriber;
import lombok.NonNull;

public class Service {
    private final PublisherFactory publisherFactory;
    private final SubscriberFactory subscriberFactory;
    private final TopicFactory topicFactory;

    public Service(){
        this.topicFactory = new TopicFactory();
        this.publisherFactory = new PublisherFactory(topicFactory);
        this.subscriberFactory = new SubscriberFactory(topicFactory);
    }

    public Publisher getPublisher(@NonNull String publisherId){
        return this.publisherFactory.getPublisher(publisherId);
    }

    public Subscriber getSubscriber(@NonNull String subscriberId){
        return this.subscriberFactory.getSubscriber(subscriberId);
    }

}
