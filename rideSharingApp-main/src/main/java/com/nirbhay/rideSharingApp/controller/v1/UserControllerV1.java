package com.nirbhay.rideSharingApp.controller.v1;

import com.nirbhay.rideSharingApp.model.Ride;
import com.nirbhay.rideSharingApp.service.RideService;
import com.nirbhay.rideSharingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class UserControllerV1 {

    @Autowired
    private RideService rideService;

    @Autowired
    private UserService userService;

    @GetMapping("/rides")
    public List<Ride> getUserRides() {
        var user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userService.loadByUsernameSimple(user.getUsername()).getId();

        return rideService.getAll().stream().filter(ride -> ride.getUserId().equals(userId)).collect(Collectors.toList());
    }
}
