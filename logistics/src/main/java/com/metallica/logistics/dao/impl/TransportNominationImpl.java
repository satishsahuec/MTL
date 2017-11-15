package com.metallica.logistics.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metallica.logistics.dao.TransportNomination;
import com.metallica.logistics.repository.TransportNominationRepository;
@Component
public class TransportNominationImpl {

	@Autowired
	TransportNominationRepository nominationRepo;
	
	public void addNomination(TransportNomination nomination) {
		//Single transport contain only one trade as of now it may contain multiple trades ie commodity 
		nominationRepo.save(nomination);

	}

}
