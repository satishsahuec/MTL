package com.metallica.trade.listener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metallica.trade.dao.util.SearchCriteria;
import com.metallica.trade.dao.Trade;
import com.metallica.trade.dao.impl.TradeImpl;

@Component
public class ReqResTradeListner {

	@Autowired
	TradeImpl dao;

	@RabbitListener(queues = "tradeReqRes")
	public String getTrade(byte[] message) throws JsonParseException, JsonMappingException, IOException {

		String mes = new String(message);
		System.out.println("Received   Message " + mes + "");
		SearchCriteria searchCriteria = new ObjectMapper().readValue(mes, SearchCriteria.class);

		
		System.out.println("searchCriteria = "+searchCriteria);	
		
		return dao.getTrades(searchCriteria);



}

}
