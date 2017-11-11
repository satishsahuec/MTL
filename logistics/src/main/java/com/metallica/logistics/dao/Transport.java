package com.metallica.logistics.dao;

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
	private String loadingDate ;
	private String unloadingDate ;
	private TransportType transportType;
	
	public Transport() {
		// TODO Auto-generated constructor stub
	}
	public Transport(String transportId, String origin, String destination, String loadingDate, String unloadingDate,
			TransportType transportType) {
		super();
		this.transportId = transportId;
		this.origin = origin;
		this.destination = destination;
		this.loadingDate = loadingDate;
		this.unloadingDate = unloadingDate;
		this.transportType = transportType;
	}
	
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
	public String getLoadingDate() {
		return loadingDate;
	}
	public void setLoadingDate(String loadingDate) {
		this.loadingDate = loadingDate;
	}
	public String getUnloadingDate() {
		return unloadingDate;
	}
	public void setUnloadingDate(String unloadingDate) {
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
