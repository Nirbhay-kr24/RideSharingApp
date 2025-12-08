package com.nirbhay.rideSharingApp.controller.v1;

import com.nirbhay.rideSharingApp.dto.CreateRideDTO;
import com.nirbhay.rideSharingApp.exception.BadRequestException;
import com.nirbhay.rideSharingApp.exception.NotFoundException;
import com.nirbhay.rideSharingApp.model.Ride;
import com.nirbhay.rideSharingApp.service.RideService;
import com.nirbhay.rideSharingApp.service.UserService;
import com.nirbhay.rideSharingApp.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/rides")
public class RideControllerV1 {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RideService rideService;

    @Autowired
    private UserService userService;

    @PostMapping
    private ResponseEntity<Ride> createRide(@Valid @RequestBody CreateRideDTO createRideDTO) {
        Ride ride = new Ride();

        var user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userService.loadByUsernameSimple(user.getUsername()).getId();

        ride.setUserId(userId);
        ride.setStatus("REQUESTED");
        ride.setPickupLocation(createRideDTO.getPickupLocation());
        ride.setDropLocation(createRideDTO.getDropLocation());
        ride.setCreatedAt(new Date());

        return ResponseEntity.ok(rideService.save(ride));
    }

    @PostMapping("/{rideId}/accept")
    private ResponseEntity<Ride> acceptRide(@PathVariable String rideId) {
        Ride ride = rideService.get(rideId);

        if (ride == null) throw new NotFoundException("Ride Not Found");
        if (!ride.getStatus().equals("REQUESTED")) throw new BadRequestException("Ride Not Requested");

        ride.setStatus("ACCEPTED");

        return ResponseEntity.ok(rideService.save(ride));
    }

    @PostMapping("/{rideId}/complete")
    private ResponseEntity<Ride> completeRide(@PathVariable String rideId) {
        Ride ride = rideService.get(rideId);

        if (ride == null) throw new NotFoundException("Ride Not Found");
        if (!ride.getStatus().equals("ACCEPTED")) throw new BadRequestException("Ride Not Accepted");

        ride.setStatus("COMPLETED");

        return ResponseEntity.ok(rideService.save(ride));
    }
}
