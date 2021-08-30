package com.priyantanu.NotificationService;

import com.priyantanu.NotificationService.Models.Message;
import com.priyantanu.NotificationService.Models.Publisher;
import com.priyantanu.NotificationService.Models.Subscriber;
import com.priyantanu.NotificationService.Request.PublishMessageRequest;
import com.priyantanu.NotificationService.Resources.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
@RestController
public class NotificationServiceApplication {
	private final Service service = new Service();
	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@GetMapping("/createPublisher/{publisherId}")
	public Publisher getPublisher(@PathVariable(value = "publisherId") String publisherId){
		return this.service.getPublisher(publisherId);
	}

	@PutMapping("/publishMessage/{topicName}")
	public boolean publishMessage(@PathVariable(value = "topicName") String topicName, @RequestBody PublishMessageRequest publishMessageRequest){
		Publisher publisher =  this.service.getPublisher(publishMessageRequest.getPublisherId());
		return publisher.publishMessage(topicName, publishMessageRequest.getMessage());
	}

	@GetMapping("/getNextMessage/{topicName}")
	public Message getNextMessage(@PathVariable(value = "topicName") String topicName, @RequestBody String subscriberId){
		Subscriber subscriber =  this.service.getSubscriber(subscriberId);
		return subscriber.getNextMessage(topicName);
	}

	@GetMapping("/resetOffset/{topicName}")
	public boolean resetOffset(@PathVariable(value = "topicName") String topicName, @RequestBody String subscriberId){
		Subscriber subscriber = this.service.getSubscriber(subscriberId);
		return subscriber.clearSubscriberOffset(topicName);
	}

}
