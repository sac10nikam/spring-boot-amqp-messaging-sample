package com.company.rabbitmqconfig;

import static com.company.rabbitmqconfig.MessagingConstants.DIRECT_EXCHANGE_NAME;
import static com.company.rabbitmqconfig.MessagingConstants.DIRECT_QUEUE_GENERIC_NAME;
import static com.company.rabbitmqconfig.MessagingConstants.EXCHANGE_NAME;
import static com.company.rabbitmqconfig.MessagingConstants.FANOUT_EXCHANGE_NAME;
import static com.company.rabbitmqconfig.MessagingConstants.FANOUT_QUEUE_GENERIC_NAME;
import static com.company.rabbitmqconfig.MessagingConstants.QUEUE_GENERIC_NAME;
import static com.company.rabbitmqconfig.MessagingConstants.QUEUE_SPECIFIC_NAME;
import static com.company.rabbitmqconfig.MessagingConstants.ROUTING_KEY;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableRabbit
public class MessagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingApplication.class, args);
	}

	@Bean
	public TopicExchange appExchange() {
		return new TopicExchange(EXCHANGE_NAME.getValue());
	}
	
	@Bean
	public FanoutExchange appFanoutExchange() {
		return new FanoutExchange(FANOUT_EXCHANGE_NAME.getValue());
	}
	
	@Bean
	public DirectExchange appDirectExchange() {
		return new DirectExchange(DIRECT_EXCHANGE_NAME.getValue());
	}

	@Bean
	public Queue appQueueGeneric() {
		return new Queue(QUEUE_GENERIC_NAME.getValue());
	}

	@Bean
	public Queue appQueueSpecific() {
		return new Queue(QUEUE_SPECIFIC_NAME.getValue());
	}
	
	@Bean
	public Queue appFanoutQueueGeneric() {
		return new Queue(FANOUT_QUEUE_GENERIC_NAME.getValue());
	}

	@Bean
	public Queue appDirectQueueGeneric() {
		return new Queue(DIRECT_QUEUE_GENERIC_NAME.getValue());
	}

	@Bean
	public Binding declareBindingGeneric() {
		return BindingBuilder.bind(appQueueGeneric()).to(appExchange())
				.with(ROUTING_KEY.getValue());
	}

	@Bean
	public Binding declareBindingSpecific() {
		return BindingBuilder.bind(appQueueSpecific()).to(appExchange())
				.with(ROUTING_KEY.getValue());
	}
	
	@Bean
	public Binding declareDirectBindingGeneric() {
		return BindingBuilder.bind(appDirectQueueGeneric()).to(appDirectExchange())
				.with(ROUTING_KEY.getValue());
	}
	
	
	@Bean
	public Binding declareFanoutBindingGeneric() {
		return BindingBuilder.bind(appFanoutQueueGeneric()).to(appFanoutExchange());
	}

	// You can comment the two methods below to use the default serialization /
	// de-serialization (instead of JSON)
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
