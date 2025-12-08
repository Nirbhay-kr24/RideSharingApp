package com.nirbhay.rideSharingApp.service;

import com.nirbhay.rideSharingApp.model.Ride;
import com.nirbhay.rideSharingApp.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public Ride save(Ride input) {
        return rideRepository.save(input);
    }

    public Ride get(String id)  {
        return rideRepository.findById(id).orElse(null);
    }

    public List<Ride> getAll() {
        return rideRepository.findAll();
    }
}
