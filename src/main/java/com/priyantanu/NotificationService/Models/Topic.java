package com.priyantanu.NotificationService.Models;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Topic {
    @Getter
    private final String topicName;
    private final TopicOffset offset;
    private final List<Message> messageList;

    public Topic(String topicName){
        this.topicName = topicName;
        this.offset = new TopicOffset();
        this.messageList = new ArrayList<>();
    }

    private Boolean increaseMessageOffset(){
        offset.setMessageOffset(offset.getMessageOffset() + 1) ;
        return Boolean.TRUE;
    }

    synchronized public Boolean publishMessage(@NonNull Message message){
        this.messageList.add(message);
        increaseMessageOffset();
        return true;
    }

    synchronized public Message getNextMessage(@NonNull String subscriberId){
        HashMap<String, Integer> subscriberOffset = offset.getSubscriberOffset();
        if(!subscriberOffset.containsKey(subscriberId)){
            subscriberOffset.put(subscriberId,0);
        }
        if(subscriberOffset.get(subscriberId) < offset.getMessageOffset()){
            Message message = messageList.get(subscriberOffset.get(subscriberId));
            subscriberOffset.put(subscriberId,subscriberOffset.get(subscriberId)+1);
            return message;
        }
        return null;
    }

    public boolean areNewMessagesPresent(@NonNull String subscriberId){
        return this.offset.getSubscriberOffset().get(subscriberId) < this.offset.getMessageOffset();
    }

    public Boolean clearSubscriberOffset(@NonNull String subscriberId){
        HashMap<String,Integer> subscriberOffset = this.offset.getSubscriberOffset();
        if(subscriberOffset.containsKey(subscriberId)){
            subscriberOffset.put(subscriberId,0);
            return true;
        }
        return false;
    }
}
