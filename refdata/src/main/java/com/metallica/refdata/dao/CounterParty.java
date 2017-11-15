package com.metallica.refdata.dao;

public class CounterParty {
	
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
	@Override
	public String toString() {
		return "CounterParty [code=" + code + ", name=" + name + "]";
	}
	public CounterParty(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public CounterParty() {
		// TODO Auto-generated constructor stub
	}
	

}
