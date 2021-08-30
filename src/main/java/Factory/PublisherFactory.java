package Factory;


import Models.Publisher;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PublisherFactory {
    private final List<Publisher> publisherList;
    private final TopicFactory topicFactory;

    public PublisherFactory(@NonNull TopicFactory topicFactory) {
        this.topicFactory = topicFactory;
        this.publisherList = new ArrayList<>();
    }

    public Publisher getPublisher(@NonNull String publisherId){
        Publisher publisherResponse;
        for(Publisher publisher: publisherList){
            if(publisher.getPublisherId().equals(publisherId))
                return publisher;
        }
        publisherResponse = new Publisher(publisherId, topicFactory);
        publisherList.add(publisherResponse);
        return publisherResponse;
    }
}
