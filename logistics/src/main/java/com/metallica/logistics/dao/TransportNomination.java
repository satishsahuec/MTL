package com.metallica.logistics.dao;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class TransportNomination {
	
	private String buyTradeId;
	private String sellTradeId;
	@Id
	private String transportId;
	
	public String getBuyTradeId() {
		return buyTradeId;
	}
	public void setBuyTradeId(String buyTradeId) {
		this.buyTradeId = buyTradeId;
	}
	public String getSellTradeId() {
		return sellTradeId;
	}
	public void setSellTradeId(String sellTradeId) {
		this.sellTradeId = sellTradeId;
	}
	public String getTransportId() {
		return transportId;
	}
	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}

}
