package com.metallica.trade.dao.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchCriteria {
	
	private String buySide;
	private String sellSide;
	private Date totradeDate;
	private Date fromtradeDate;
	private String counterParty;
	private String commodity;
	private String location;
	
	public String getBuySide() {
		return buySide;
	}
	public void setBuySide(String buySide) {
		this.buySide = buySide;
	}
	public String getSellSide() {
		return sellSide;
	}
	public void setSellSide(String sellSide) {
		this.sellSide = sellSide;
	}
	public Date getTotradeDate() {
		return totradeDate;
	}
	public void setTotradeDate(Date totradeDate) {
		this.totradeDate = totradeDate;
	}
	public Date getFromtradeDate() {
		return fromtradeDate;
	}
	public void setFromtradeDate(Date fromtradeDate) {
		this.fromtradeDate = fromtradeDate;
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
	@Override
	public String toString() {
		return "SearchCriteria [buySide=" + buySide + ", sellSide=" + sellSide + ", totradeDate=" + totradeDate
				+ ", fromtradeDate=" + fromtradeDate + ", counterParty=" + counterParty + ", commodity=" + commodity
				+ ", location=" + location + "]";
	}
	
}
