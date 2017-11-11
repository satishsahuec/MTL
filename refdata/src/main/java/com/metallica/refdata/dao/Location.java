package com.metallica.refdata.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Location {

	
	@Id
	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location() {
		// TODO Auto-generated constructor stub
	}
	public Location(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	@Override
	public String toString() {
		return "Location [name=" + name + ", code=" + code + "]";
	}
	

}
