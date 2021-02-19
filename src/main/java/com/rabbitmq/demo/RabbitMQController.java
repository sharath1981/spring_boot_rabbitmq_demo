package com.rabbitmq.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

	private final RabbitTemplate rabbitTemplate;

	@GetMapping("publish/{message}")
	public String publish(@PathVariable String message) {
		rabbitTemplate.convertAndSend("SHARATH_QUEUE", message);
		return "PUBLISHED";
	}

	@RabbitListener(queues = "SHARATH_QUEUE")
	public void receiver(final String message) {
		log.info("RECEIVED ==> {}", message);
	}

}
