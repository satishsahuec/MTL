package com.metallica.refdata.dao;

public class Commodity {

	private String name;
	private String code;
	
	
	public Commodity() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public Commodity(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	@Override
	public String toString() {
		return "Commodity [name=" + name + ", code=" + code + "]";
	}

}
