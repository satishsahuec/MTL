package com.metallica.refdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.metallica.refdata.dao.CounterParty;


public interface CounterPartyRepository extends MongoRepository<CounterParty, String>

{

}
