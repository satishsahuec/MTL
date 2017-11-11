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

	/*
	 * @Bean public DirectExchange tradeEvent() { return new
	 * DirectExchange("tradeEvent"); }
	 * 
	 * @Bean public DirectExchange tradeReq() { return new
	 * DirectExchange("tradeReq"); }
	 */

	private static class ReceiverConfig {

		@Bean
		public Queue autoDeleteQueue1() {
			return new Queue("tradeEvent");
		}

		@Bean
		public Queue autoDeleteQueue2() {
			return new Queue("tradeCommand");
		}

		@Bean
		public Queue autoDeleteQueue3() {
			return new Queue("tradeReqRes");
		}

		@Bean
		public Binding binding1a(DirectExchange trade, Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(trade).with("tradeEvent");
		}

		@Bean
		public Binding binding1b(DirectExchange trade, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(trade).with("tradeCommand");
		}

		@Bean
		public Binding binding1c(DirectExchange trade, Queue autoDeleteQueue3) {
			return BindingBuilder.bind(autoDeleteQueue3).to(trade).with("tradeReqRes");
		}

	}

}
