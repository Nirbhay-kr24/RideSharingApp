package com.nirbhay.rideSharingApp.repository;

import com.nirbhay.rideSharingApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
