package com.metallica.logistics.dao;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.metallica.logistics.TransportType;

@Document
@JsonIgnoreProperties(ignoreUnknown=true)
public class Transport {
	
	@Id
	private String transportId;
	private String origin ;
	private String destination ;
	private Date loadingDate ;
	private Date unloadingDate ;
	private TransportType transportType;
		
	public String getTransportId() {
		return transportId;
	}
	public void setId(String transportId) {
		this.transportId = transportId;
	}
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
	public Date getLoadingDate() {
		return loadingDate;
	}
	public void setLoadingDate(Date loadingDate) {
		this.loadingDate = loadingDate;
	}
	public Date getUnloadingDate() {
		return unloadingDate;
	}
	public void setUnloadingDate(Date unloadingDate) {
		this.unloadingDate = unloadingDate;
	}
	public TransportType getTransportType() {
		return transportType;
	}
	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}
	@Override
	public String toString() {
		return "Transport [transportid=" + transportId + ", origin=" + origin + ", destination=" + destination
				+ ", loadingDate=" + loadingDate + ", unloadingDate=" + unloadingDate + ", transportType="
				+ transportType + "]";
	}
	
	

}
