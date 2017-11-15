package com.metallica.refdata.rabbit.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessaageConfiguration {

	@Autowired
	private RabbitTemplate template;

	@Bean
	public DirectExchange refdata() {
		return new DirectExchange("refdata");
	}

	private static class ReceiverConfig {

		@Bean
		public Queue refdataEvent() {
			return new Queue("refdataEvent");
		}

		@Bean
		public Queue refdataCommand() {
			return new Queue("refdataCommand");
		}

		@Bean
		public Queue refdataReqRes() {
			return new Queue("refdataReqRes");
		}

		@Bean
		public Queue refdatavalidation() {
			return new Queue("refdatavalidation");
		}

		@Bean
		public Queue refdatatransportValidation() {
			return new Queue("refdatatransportValidation");
		}

		@Bean
		public Binding binding1a(DirectExchange refdata, Queue refdataEvent) {
			return BindingBuilder.bind(refdataEvent).to(refdata).with("refdataEvent");
		}

		@Bean
		public Binding binding1b(DirectExchange refdata, Queue refdataCommand) {
			return BindingBuilder.bind(refdataCommand).to(refdata).with("refdataCommand");
		}

		@Bean
		public Binding binding1c(DirectExchange refdata, Queue refdataReqRes) {
			return BindingBuilder.bind(refdataReqRes).to(refdata).with("refdataReqRes");
		}

		@Bean
		public Binding binding1d(DirectExchange refdata, Queue refdatavalidation) {
			return BindingBuilder.bind(refdatavalidation).to(refdata).with("refdatavalidation");
		}

		@Bean
		public Binding binding1e(DirectExchange refdata, Queue refdatatransportValidation) {
			return BindingBuilder.bind(refdatatransportValidation).to(refdata).with("refdatatransportValidation");
		}
	}

}
