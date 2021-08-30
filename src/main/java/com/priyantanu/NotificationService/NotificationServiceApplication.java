package com.priyantanu.NotificationService;

import com.priyantanu.NotificationService.Models.Publisher;
import com.priyantanu.NotificationService.Resources.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServiceApplication {

	private static final String PUBLISHER_NAME = "rama";
	private static final String TOPIC_NAME = "topic";

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
		Service service = new Service();

		for(int publisherCount = 0; publisherCount < 2; publisherCount++){
			for(int messsageCount = 0; messsageCount<3; messsageCount++){
				Publisher publisher = service.getPublisher(PUBLISHER_NAME + publisherCount);
				publisher.publishMessage(TOPIC_NAME + publisherCount, "message" + messsageCount);
			}
		}
	}

}
