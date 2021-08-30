package com.priyantanu.NotificationService.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class TopicOffset {
    private Integer messageOffset;
    private HashMap<String, Integer> subscriberOffset;
    public TopicOffset(){
        this.messageOffset = 0;
        this.subscriberOffset = new HashMap<>();
    }
}
