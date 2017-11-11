package com.metallica.trade.dao;

import org.springframework.data.annotation.Id;

public class Counter {
	
	@Id
	private String _id ;
	private long seq ;
	public Counter(String _id, long seq) {
		super();
		this._id = _id;
		this.seq = seq;
	}
	public String getUser() {
		return _id;
	}
	public void setUser(String _id) {
		this._id = _id;
	}
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	

}
