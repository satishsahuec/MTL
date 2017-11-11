package com.metallica.logistics.listener;

import java.io.IOException;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metallica.logistics.dao.Action;
import com.metallica.logistics.dao.Transport;
import com.metallica.logistics.dao.TransportImpl;
import com.metallica.logistics.dao.TransportNomination;
import com.metallica.logistics.dao.TransportNominationImpl;
import com.metallica.logistics.repository.CounterService;
import com.metallica.logistics.validation.LocationValidation;

@Component
public class CommandTransportListner {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	TransportImpl transportImpl;

	@Autowired
	TransportNominationImpl nominationImpl;
	@Autowired
	private CounterService counterService;

	@Autowired
	private LocationValidation locationValidation;

	@RabbitListener(queues = "logisticCommand")
	public String addTransport(byte[] message) throws JsonParseException, JsonMappingException, IOException {

		String mes = new String(message);
		System.out.println("Transport Received   Message " + mes + "");
		Transport transportData = new ObjectMapper().readValue(mes, Transport.class);

		Action action = new ObjectMapper().readValue(mes, Action.class);

		if (action.getCommand().equalsIgnoreCase(Action.ADD)) {
			
			/*if (!locationValidation.validateTransport(transportData))
				return "Invalid Transport Data";
			*/
			transportData.setId("" + counterService.getNextSequence("user"));
			System.out.println("transportData " + transportData);
			transportImpl.addTransport(transportData);
		} else if (action.getCommand().equalsIgnoreCase(Action.DELETE)) {
			return transportImpl.deleteTransport(transportData.getTransportId());

		} else if (action.getCommand().equalsIgnoreCase(Action.UPDATE)) {
			/*if (!locationValidation.validateTransport(transportData))
				return "Invalid Transport Data";
*/					ObjectMapper mapper = new ObjectMapper();
			Transport updateResult = transportImpl.updatetrade(transportData);
			return mapper.writeValueAsString(updateResult);
		} else if (action.getCommand().equalsIgnoreCase(Action.NOMINATE)) {
			TransportNomination transportNom = new ObjectMapper().readValue(mes, TransportNomination.class);
			nominateTransport(transportNom);
			return "Transport Nominatied ";
		}

		return "" + transportData.getTransportId();

	}

	public void nominateTransport(TransportNomination transportNom) {

		nominationImpl.addNomination(transportNom);

		template.convertSendAndReceive("trade", "tradeEvent", transportNom.getBuyTradeId());
		template.convertSendAndReceive("trade", "tradeEvent", transportNom.getSellTradeId());

	}

}
