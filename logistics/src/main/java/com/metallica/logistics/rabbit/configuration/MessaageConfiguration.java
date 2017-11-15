package com.metallica.logistics.rabbit.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//Configuration for Rabbit Message Broker
public class MessaageConfiguration {
	// tradeEvent , tradeCommand, tradeReq

	@Autowired
	private RabbitTemplate template;

	@Bean
	public DirectExchange logistic() {
		return new DirectExchange("logistic");
	}

	private static class ReceiverConfig {

		@Bean
		public Queue logisticCommand() {
			return new Queue("logisticCommand");
		}

		@Bean
		public Queue logisticEvent() {
			return new Queue("logisticEvent");
		}

		@Bean
		public Queue logisticReqRes() {
			return new Queue("logisticReqRes");
		}

		@Bean
		public Binding binding1a(DirectExchange logistic, Queue logisticCommand) {
			return BindingBuilder.bind(logisticCommand).to(logistic).with("logisticCommand");
		}

		@Bean
		public Binding binding1b(DirectExchange logistic, Queue logisticEvent) {
			return BindingBuilder.bind(logisticEvent).to(logistic).with("logisticEvent");
		}

		@Bean
		public Binding binding1c(DirectExchange logistic, Queue logisticReqRes) {
			return BindingBuilder.bind(logisticReqRes).to(logistic).with("logisticReqRes");
		}

	}

}
