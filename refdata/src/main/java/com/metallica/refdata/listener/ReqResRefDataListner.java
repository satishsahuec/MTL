package com.metallica.refdata.listener;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metallica.refdata.dao.Action;
import com.metallica.refdata.dao.Commodity;
import com.metallica.refdata.dao.CommodityImpl;
import com.metallica.refdata.dao.CounterParty;
import com.metallica.refdata.dao.CounterPartyImpl;
import com.metallica.refdata.dao.Location;
import com.metallica.refdata.dao.LocationImpl;

@Component
public class ReqResRefDataListner {

	@Autowired
	CommodityImpl commodityImpl;

	@Autowired
	CounterPartyImpl counterPartyImpl;

	@Autowired
	LocationImpl locationImpl;

	@RabbitListener(queues = "refdataReqRes")
	public void getRefData(byte[] inputMessage) throws JsonParseException, JsonMappingException, IOException {

		System.out.println("message == " + inputMessage.toString());

		String message = inputMessage.toString();

		Action action = new ObjectMapper().readValue(message, Action.class);
		Commodity commodityData = null;
		CounterParty counterPartyData = null;
		Location locationData = null;
		if (action.getCommand().equalsIgnoreCase(Action.COMMODITY)) {
			commodityData = new ObjectMapper().readValue(message, Commodity.class);
			commodityImpl.getCommodity(commodityData);
		} else if (action.getCommand().equalsIgnoreCase(Action.COUNTERPARTY)) {
			counterPartyData = new ObjectMapper().readValue(message, CounterParty.class);
			counterPartyImpl.getCounterParty(counterPartyData);
		} else if (action.getCommand().equalsIgnoreCase(Action.LOCATION)) {
			locationData = new ObjectMapper().readValue(message, Location.class);
			locationImpl.getLocation(locationData);
		}

	}

}
