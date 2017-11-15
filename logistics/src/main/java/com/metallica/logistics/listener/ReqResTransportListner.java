package com.metallica.logistics.listener;

import java.io.IOException;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metallica.logistics.dao.impl.TransportImpl;
import com.metallica.logistics.dao.util.SearchCriteriaTransport;


@Component
public class ReqResTransportListner {

	@Autowired
	TransportImpl dao;

	@RabbitListener(queues = "logisticReqRes")
	public String getTransports(byte[] message) throws JsonParseException, JsonMappingException, IOException {
		
		String byteToStr=new String(message);
		System.out.println("byteToStr " + byteToStr);
		SearchCriteriaTransport searchCriteria = new ObjectMapper().readValue(byteToStr, SearchCriteriaTransport.class);
		
		return dao.getTransports(searchCriteria);

	}

}
