package com.example.currencyexchangeservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.currencyexchangeservice.model.ExchangeValue;

public interface ExchangeValueRepo extends MongoRepository<ExchangeValue, Long>
{

	public ExchangeValue findByFromAndTo(String from, String to);

	//public Optional<ExchangeValue> findById(String id);


}
