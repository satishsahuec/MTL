package com.metallica.logistics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.metallica.logistics.dao.Transport;

public interface TransportRepository extends MongoRepository<Transport, String> {

}
