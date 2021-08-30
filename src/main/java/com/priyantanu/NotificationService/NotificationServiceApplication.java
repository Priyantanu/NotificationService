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
	public Publisher createPublisher(@PathVariable(value = "publisherId") String publisherId){
		Publisher publisher = this.service.getPublisher(publisherId);
		System.out.println("created publisher "+ publisher);
		return publisher;
	}

	@GetMapping("/createSubscriber/{subscriberId}")
	public Subscriber createSubscriber(@PathVariable(value = "subscriberId") String subscriberId){
		Subscriber subscriber = this.service.getSubscriber(subscriberId);
		System.out.println("created subscriber "+ subscriber);
		return subscriber;
	}

	@PutMapping("/publishMessage/{topicName}")
	public boolean publishMessage(@PathVariable(value = "topicName") String topicName, @RequestBody PublishMessageRequest publishMessageRequest){
		Publisher publisher =  this.service.getPublisher(publishMessageRequest.getPublisherId());
		boolean isMessagePublished =  publisher.publishMessage(topicName, publishMessageRequest.getMessage());
		System.out.println("message published " + isMessagePublished + " " + publishMessageRequest.message);
		return isMessagePublished;
	}

	@PutMapping("/getNextMessage/{topicName}")
	public Message getNextMessage(@PathVariable(value = "topicName") String topicName, @RequestBody String subscriberId){
		Subscriber subscriber =  this.service.getSubscriber(subscriberId);
		Message message = subscriber.getNextMessage(topicName);
		System.out.println("next message is provided to subscriber "+ subscriberId + " " + topicName);
		if(message == null){
			return new Message("null");
		}
		return message;
	}

	@PutMapping("/resetOffset/{topicName}")
	public boolean resetOffset(@PathVariable(value = "topicName") String topicName, @RequestBody String subscriberId){
		Subscriber subscriber = this.service.getSubscriber(subscriberId);
		System.out.println("reset offset occured");
		return subscriber.clearSubscriberOffset(topicName);
	}

}
