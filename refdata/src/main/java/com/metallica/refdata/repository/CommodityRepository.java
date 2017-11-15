package com.metallica.refdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.metallica.refdata.dao.Commodity;

public interface CommodityRepository extends MongoRepository<Commodity, String>

{

}
