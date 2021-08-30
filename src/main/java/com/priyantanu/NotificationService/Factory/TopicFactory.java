package com.priyantanu.NotificationService.Factory;

import com.priyantanu.NotificationService.Models.Topic;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TopicFactory {
    private final List<Topic> topicList;

    public TopicFactory(){
        this.topicList = new ArrayList<Topic>();
    }

    public boolean createTopic(String topicName){
        boolean isTopicPresent =  topicList.stream().anyMatch(topic ->
             topic.getTopicName().equals(topicName)
        );
        if(isTopicPresent){
            return true;
        }
        try{
                topicList.add(new Topic(topicName));
                return true;
        }catch (Exception ex){
            System.out.println("unable to create topic"  + ex);
        }
        return false;
    }

    public Topic getTopic(@NonNull String topicName){
        if(!topicList.isEmpty()){
            for(Topic topic: topicList){
                if(topic.getTopicName().equals(topicName)){
                    return topic;
                }
            }
        }
        return null;
    }

    public boolean deleteTopic(String topicName){
        for(Topic topic: this.topicList){
            if(topic.getTopicName().equals(topicName)){
                this.topicList.remove(topic);
                return true;
            }
        }
        return false;
    }
}
