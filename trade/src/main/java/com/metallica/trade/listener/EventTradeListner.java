package com.metallica.trade.listener;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metallica.trade.TradeStatus;
import com.metallica.trade.dao.Trade;
import com.metallica.trade.dao.TradeImpl;

public class EventTradeListner {

	@Autowired
	TradeImpl tradeImpl;

	@RabbitListener(queues = "tradeEvent")
	public Trade nominateTrade(byte[] message) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();

		HashMap<String, String> result = new ObjectMapper().readValue(message.toString(), HashMap.class);
		Trade data = null;
		if (result.get("buyTradeId") != null) {
			data = tradeImpl.getTrade(result.get("buyTradeId"));
			data.setTradeStatus(TradeStatus.NOMINATED);
			tradeImpl.updatetrade(data);
		} else {
			data = tradeImpl.getTrade(result.get("sellTradeId"));
			data.setTradeStatus(TradeStatus.NOMINATED);
			tradeImpl.updatetrade(data);

		}

		return data;

	}

}
