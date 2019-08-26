package com.company.rabbitmqconfig;

import static com.company.rabbitmqconfig.MessagingConstants.EXCHANGE_NAME;
import static com.company.rabbitmqconfig.MessagingConstants.ROUTING_KEY;

import java.util.Random;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomMessageProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Async
	@Scheduled(fixedDelay = 3000L)
	public void sendMessage() {
		CustomMessage message = new CustomMessage("Hello there!",
				new Random().nextInt(50), false);
		log.info("Sending message...");
		rabbitTemplate.convertAndSend(EXCHANGE_NAME.getValue(),
				ROUTING_KEY.getValue(), message);
	}
}
