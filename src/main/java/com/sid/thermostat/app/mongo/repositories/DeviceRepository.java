package com.sid.thermostat.app.mongo.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sid.thermostat.app.mongo.entites.Device;

@Repository
public interface DeviceRepository extends MongoRepository<Device, ObjectId> {
	Optional<Device> findBySerialNo(String serialNo);
}
