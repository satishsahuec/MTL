package com.metallica.logistics.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.metallica.logistics.repository.TransportNominationRepository;
@Component
public class TransportNominationImpl {

	@Autowired
	TransportNominationRepository nominationRepo;
	
	public void addNomination(TransportNomination nomination) {

		nominationRepo.save(nomination);

	}

}
