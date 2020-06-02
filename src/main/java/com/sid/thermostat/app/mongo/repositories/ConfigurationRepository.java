package com.sid.thermostat.app.mongo.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sid.thermostat.app.mongo.entites.Config;

@Repository
public interface ConfigurationRepository extends MongoRepository<Config, ObjectId> {
}
