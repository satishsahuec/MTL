package com.metallica.trade.dao.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {

	public final static String ADD = "add";
	public final static String DELETE = "delete";
	public final static String UPDATE = "update";
	public final static String FIND = "find";

	private String command;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
