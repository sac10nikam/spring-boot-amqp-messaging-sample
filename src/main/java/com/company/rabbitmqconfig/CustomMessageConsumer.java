package com.company.rabbitmqconfig;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomMessageConsumer {

	@RabbitListener(queues = "appGenericQueue")
	public void receiveMessage(final Message message) {
		log.info("Received message as generic: {}", message.toString());
	}

	@RabbitListener(queues = "appSpecificQueue")
	public void receiveMessage(final CustomMessage customMessage) {
		log.info("Received message as specific class: {}",
				customMessage.toString());
	}
	
	@RabbitListener(autoStartup="false",
            bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(name="myexchange",
                                 type=ExchangeTypes.FANOUT)))
    public void processMessage(final CustomMessage customMessage) {
		log.info("Received fanout message as specific class: {}",
				customMessage.toString());
    }
}
