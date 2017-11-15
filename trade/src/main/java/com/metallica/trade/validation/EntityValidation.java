package com.metallica.trade.validation;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.metallica.trade.dao.Trade;

@Component
public class EntityValidation {

	@Autowired
	private RabbitTemplate template;

	public boolean validateTrade(Trade tradeData) throws AmqpException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		Boolean response = (Boolean) template.convertSendAndReceive("refdata", "refdatavalidation",mapper.writeValueAsString(tradeData));
		System.out.println(" Got response from Refdata validation of Entity '" + response + "'");
		return response;
	}

}
