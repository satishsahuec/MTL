package com.metallica.trade.listener;

import java.io.IOException;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metallica.trade.TradeStatus;
import com.metallica.trade.dao.Trade;
import com.metallica.trade.dao.impl.TradeImpl;
import com.metallica.trade.dao.util.Action;
import com.metallica.trade.dao.util.CounterService;
import com.metallica.trade.validation.EntityValidation;

@Component
public class CommandTradeListner {
	@Autowired
	TradeImpl tradeImpl;
	@Autowired
	private CounterService counterService;
	@Autowired
	private EntityValidation tradeValidation;

	@RabbitListener(queues = "tradeCommand")
	public String addTrade(byte[] message) throws JsonParseException, JsonMappingException, IOException {
		String mes = new String(message);
		System.out.println("Trade Received   Message " + mes + "");
		Trade tradeData = new ObjectMapper().readValue(mes, Trade.class);

		Action action = new ObjectMapper().readValue(mes, Action.class);

		if (action.getCommand().equalsIgnoreCase(Action.ADD)) {
			if (!tradeValidation.validateTrade(tradeData))
				return "Invalid Data";
			tradeData.setTradeId("" + counterService.getNextSequence("user"));
			System.out.println("tradeData " + tradeData);
			tradeImpl.addtrade(tradeData);
		} else if (action.getCommand().equalsIgnoreCase(Action.DELETE)) {
			return tradeImpl.deleteTrade(tradeData.getTradeId());

		} else if (action.getCommand().equalsIgnoreCase(Action.UPDATE)) {
			
			System.out.println("CommandTradeListner.addTrade()");
			if (!tradeValidation.validateTrade(tradeData))
				return "Invalid Data for Ref Services ";
			
			ObjectMapper mapper = new ObjectMapper();
			
			Trade t =tradeImpl.updatetrade(tradeData);
			return mapper.writeValueAsString(t);

		}

		return "" + tradeData.getTradeId();

	}

}
