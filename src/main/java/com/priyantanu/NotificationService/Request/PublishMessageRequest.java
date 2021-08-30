package com.priyantanu.NotificationService.Request;

import com.priyantanu.NotificationService.Models.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishMessageRequest {
    public String publisherId;
    public Message message;
}
