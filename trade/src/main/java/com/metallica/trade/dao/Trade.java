package com.metallica.trade.dao;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.metallica.trade.Side;
import com.metallica.trade.TradeStatus;

@Document
@JsonIgnoreProperties(ignoreUnknown=true)
public class Trade implements Serializable   {
	@Id
	private String tradeId;
	private Side side;
	private int quantity;
	private double price;
	private Date tradeDate;
	private TradeStatus tradeStatus;
	private String counterParty;
	private String commodity;
	private String location;

	public Trade() {
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	@Override
	public String toString() {
		return "tradeId=" + tradeId + ", side=" + side + ", quantity=" + quantity + ", price=" + price
				+ ", tradeDate=" + tradeDate + ", tradeStatus=" + tradeStatus + ", counterParty=" + counterParty + ", commodity="
				+ commodity + ", location=" + location + "";
	}

}
