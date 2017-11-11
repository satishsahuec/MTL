package com.metallica.refdata.rabbit.configuration;

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
	public DirectExchange refdata() {
		return new DirectExchange("refdata");
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
			return new Queue("refdataEvent");
		}

		@Bean
		public Queue autoDeleteQueue2() {
			return new Queue("refdataCommand");
		}

		@Bean
		public Queue autoDeleteQueue3() {
			return new Queue("refdataReqRes");
		}

		@Bean
		public Queue autoDeleteQueue4() {
			return new Queue("refdatavalidation");
		}

		@Bean
		public Queue autoDeleteQueue5() {
			return new Queue("refdatatransportValidation");
		}

		@Bean
		public Binding binding1a(DirectExchange refdata, Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(refdata).with("refdataEvent");
		}

		@Bean
		public Binding binding1b(DirectExchange refdata, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(refdata).with("refdataCommand");
		}

		@Bean
		public Binding binding1c(DirectExchange refdata, Queue autoDeleteQueue3) {
			return BindingBuilder.bind(autoDeleteQueue3).to(refdata).with("refdataReqRes");
		}

		@Bean
		public Binding binding1d(DirectExchange refdata, Queue autoDeleteQueue4) {
			return BindingBuilder.bind(autoDeleteQueue4).to(refdata).with("refdatavalidation");
		}

		@Bean
		public Binding binding1e(DirectExchange refdata, Queue autoDeleteQueue5) {
			return BindingBuilder.bind(autoDeleteQueue5).to(refdata).with("refdatatransportValidation");
		}
	}

}
