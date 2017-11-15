package com.metallica.trade.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.metallica.trade.dao.Trade;

public interface TradeRepository extends MongoRepository<Trade, String> {

	List<Trade> findBytradeDateBetween(Date from, Date to);

}
