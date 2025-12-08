package com.nirbhay.rideSharingApp.repository;

import com.nirbhay.rideSharingApp.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RideRepository extends MongoRepository<Ride, String> {
}
