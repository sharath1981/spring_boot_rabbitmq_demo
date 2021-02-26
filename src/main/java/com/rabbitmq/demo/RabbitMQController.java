package com.rabbitmq.demo;

import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/rabbitmq")
public class RabbitMQController {

	/**
	 *
	 */
	private static final String QUEUE = "SHARATH_QUEUE";
	private final RabbitTemplate rabbitTemplate;

	@GetMapping("publish/{message}") 
	public String publish(@PathVariable String message) {
		rabbitTemplate.convertAndSend(QUEUE, message); //Publisher
		return "PUBLISHED";
	}

	//@RabbitListener(queues = "SHARATH_QUEUE") // As a subscriber
	public void receiver(final String message) {
		log.info("RECEIVED ==> {}", message);
	}

	@GetMapping
	public String receiver() {
		return Optional.ofNullable(rabbitTemplate.receiveAndConvert(QUEUE))//p2p(point-to-point messaging)
					   .map(String.class::cast)
					   .orElse(Strings.EMPTY);
	}

}
