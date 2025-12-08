package com.nirbhay.rideSharingApp.controller.v1;

import com.nirbhay.rideSharingApp.model.Ride;
import com.nirbhay.rideSharingApp.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverContollerV1 {

    @Autowired
    private RideService rideService;

    @GetMapping("/rides/requests")
    public List<Ride> getRequestedRides() {
        return rideService.getAll().stream().filter(ride -> ride.getStatus().equals("REQUESTED")).collect(Collectors.toList());
    }

}
