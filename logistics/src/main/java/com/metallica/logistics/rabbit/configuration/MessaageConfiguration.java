package com.metallica.logistics.rabbit.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
		public Queue autoDeleteQueue1() {
			return new Queue("logisticCommand");
		}

		@Bean
		public Queue autoDeleteQueue2() {
			return new Queue("logisticEvent");
		}

		@Bean
		public Queue autoDeleteQueue3() {
			return new Queue("logisticReqRes");
		}

		@Bean
		public Binding binding1a(DirectExchange logistic, Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(logistic).with("logisticCommand");
		}

		@Bean
		public Binding binding1b(DirectExchange logistic, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(logistic).with("logisticEvent");
		}

		@Bean
		public Binding binding1c(DirectExchange logistic, Queue autoDeleteQueue3) {
			return BindingBuilder.bind(autoDeleteQueue3).to(logistic).with("logisticReqRes");
		}

	}

}
