package com.rabbitmq.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRabbitmqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRabbitmqDemoApplication.class, args);
	}

	@Bean
	public Queue myQueue() {
	    return new Queue("SHARATH_QUEUE", false);
	}
}
