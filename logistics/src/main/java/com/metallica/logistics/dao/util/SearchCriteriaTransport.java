package com.metallica.logistics.dao.util;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchCriteriaTransport {
	
	private String origin;
	private String destination;
	private Date fromLoadingDate;
	private Date toLoadingDate;
	private Date fromUnloadingDate;
	private Date toUnloadingDate;
	private String transportType;
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getFromLoadingDate() {
		return fromLoadingDate;
	}
	public void setFromLoadingDate(Date fromLoadingDate) {
		this.fromLoadingDate = fromLoadingDate;
	}
	public Date getToLoadingDate() {
		return toLoadingDate;
	}
	public void setToLoadingDate(Date toLoadingDate) {
		this.toLoadingDate = toLoadingDate;
	}
	public Date getFromUnloadingDate() {
		return fromUnloadingDate;
	}
	public void setFromUnloadingDate(Date fromUnloadingDate) {
		this.fromUnloadingDate = fromUnloadingDate;
	}
	public Date getToUnloadingDate() {
		return toUnloadingDate;
	}
	public void setToUnloadingDate(Date toUnloadingDate) {
		this.toUnloadingDate = toUnloadingDate;
	}
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	@Override
	public String toString() {
		return "SearchCriteriaTransport [origin=" + origin + ", destination=" + destination + ", fromLoadingDate="
				+ fromLoadingDate + ", toLoadingDate=" + toLoadingDate + ", fromUnloadingDate=" + fromUnloadingDate
				+ ", toUnloadingDate=" + toUnloadingDate + ", transportType=" + transportType + "]";
	}

	

}
