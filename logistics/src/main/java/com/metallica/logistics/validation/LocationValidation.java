package com.metallica.logistics.validation;


import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metallica.logistics.dao.Transport;
import com.metallica.logistics.rabbit.configuration.ServiceListner;

@Component
public class LocationValidation {

	@Autowired
	private RabbitTemplate template;
	//Validation of transport location 
	public boolean validateTransport(Transport transportdata) throws AmqpException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		Boolean response = (Boolean) template.convertSendAndReceive(ServiceListner.REFDATA_EXCHANGE_NAME,
				ServiceListner.REFDATA_ROUTING_KEY, mapper.writeValueAsString(transportdata));
		System.out.println(" Got response from Refdata validation of Transport '" + response + "'");
		return response;
	}

}
