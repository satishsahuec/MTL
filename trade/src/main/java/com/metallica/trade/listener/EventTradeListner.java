package com.metallica.trade.listener;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metallica.trade.TradeStatus;
import com.metallica.trade.dao.Trade;
import com.metallica.trade.dao.impl.TradeImpl;

@Component
public class EventTradeListner {

	@Autowired
	TradeImpl tradeImpl;

	@RabbitListener(queues = "tradeEvent")
	public String nominateTrade(String message) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("EventTradeListner.nominateTrade()" + message);
		// TradeId sperated by space in input String
		String[] tradeId = message.split(" ");

		Trade data = null;

		data = tradeImpl.getTrade(tradeId[0]);
		if (data == null) {
			return null;
		}
		data.setTradeStatus(TradeStatus.NOMINATED);
		tradeImpl.updatetrade(data);

		data = tradeImpl.getTrade(tradeId[1]);
		if (data == null) {
			return null;
		}
		data.setTradeStatus(TradeStatus.NOMINATED);
		tradeImpl.updatetrade(data);

		return "SUCCESS";

	}

}
