package com.metallica.refdata.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {

	public final static String COMMODITY = "commodity";
	public final static String COUNTERPARTY = "counterparty";
	public final static String LOCATION = "location";
	
	private String command;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
