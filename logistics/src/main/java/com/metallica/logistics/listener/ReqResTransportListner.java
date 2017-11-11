package com.metallica.logistics.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metallica.logistics.dao.Transport;
import com.metallica.logistics.dao.TransportImpl;

@Component
public class ReqResTransportListner {

	@Autowired
	TransportImpl dao;

	@RabbitListener(queues = "reqres")
	public void getTransports(byte[] message) {
		System.out.println("message " + message);
		
		System.out.println(dao.getTransports(new Transport()));

	}

}
