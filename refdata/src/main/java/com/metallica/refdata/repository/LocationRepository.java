package com.metallica.refdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.metallica.refdata.dao.Location;


public interface LocationRepository extends MongoRepository<Location, String>

{

}
