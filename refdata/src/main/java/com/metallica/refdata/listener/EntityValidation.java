package com.metallica.refdata.listener;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metallica.refdata.dao.Commodity;
import com.metallica.refdata.dao.CommodityImpl;
import com.metallica.refdata.dao.CounterParty;
import com.metallica.refdata.dao.CounterPartyImpl;
import com.metallica.refdata.dao.Location;
import com.metallica.refdata.dao.LocationImpl;

@Component
public class EntityValidation {

	@Autowired
	CommodityImpl commodityImpl;

	@Autowired
	CounterPartyImpl counterPartyImpl;

	@Autowired
	LocationImpl locationImpl;

	@RabbitListener(queues = "refdatavalidation")
	public Boolean refDataValidation(String inputMessage) throws JsonParseException, JsonMappingException, IOException {

		HashMap<String, String> result = new ObjectMapper().readValue(inputMessage, HashMap.class);

		System.out.println("EntityValidation.getRefData()" + result);
		Commodity comm = new Commodity();
		comm.setCode(result.get("commodity"));

		CounterParty counterParty = new CounterParty();
		counterParty.setCode(result.get("counterParty"));

		Location location = new Location();
		location.setCode(result.get("location"));

		return (commodityImpl.isValidCommodity(comm) && counterPartyImpl.isValidCounterParty(counterParty)
				&& locationImpl.isValidLocation(location));

	}

	@RabbitListener(queues = "refdatatransportValidation")
	public Boolean transportValidation(String inputMessage)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		HashMap<String, String> result = new ObjectMapper().readValue(inputMessage, HashMap.class);

		Location originLocation = new Location();
		originLocation.setCode(result.get("origin"));

		Location destinationLocation = new Location();
		destinationLocation.setCode(result.get("destination"));

		return (locationImpl.isValidLocation(originLocation) && locationImpl.isValidLocation(destinationLocation));

	}
}
