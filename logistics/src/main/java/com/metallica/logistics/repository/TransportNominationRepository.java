package com.metallica.logistics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.metallica.logistics.dao.TransportNomination;

public interface TransportNominationRepository  extends MongoRepository<TransportNomination, String>  {

}
