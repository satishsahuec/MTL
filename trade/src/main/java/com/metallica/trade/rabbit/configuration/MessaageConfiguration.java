package com.metallica.trade.rabbit.configuration;

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
	public DirectExchange trade() {
		return new DirectExchange("trade");
	}

	private static class ReceiverConfig {

		@Bean
		public Queue tradeEvent() {
			return new Queue("tradeEvent");
		}

		@Bean
		public Queue tradeCommand() {
			return new Queue("tradeCommand");
		}

		@Bean
		public Queue tradeReqRes() {
			return new Queue("tradeReqRes");
		}

		@Bean
		public Binding binding1a(DirectExchange trade, Queue tradeEvent) {
			return BindingBuilder.bind(tradeEvent).to(trade).with("tradeEvent");
		}

		@Bean
		public Binding binding1b(DirectExchange trade, Queue tradeCommand) {
			return BindingBuilder.bind(tradeCommand).to(trade).with("tradeCommand");
		}

		@Bean
		public Binding binding1c(DirectExchange trade, Queue tradeReqRes) {
			return BindingBuilder.bind(tradeReqRes).to(trade).with("tradeReqRes");
		}

	}

}
