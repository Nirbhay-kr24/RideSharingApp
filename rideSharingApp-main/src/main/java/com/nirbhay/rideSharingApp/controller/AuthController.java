package com.nirbhay.rideSharingApp.controller;

import com.nirbhay.rideSharingApp.model.User;
import com.nirbhay.rideSharingApp.service.UserService;
import com.nirbhay.rideSharingApp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user.getUsername(), user.getPassword(), user.getRole());
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User dbUser = service.loadByUsernameSimple(user.getUsername());

        if (dbUser == null) {
            return "User not found";
        }

        return jwtUtil.generateToken(dbUser.getUsername(), dbUser.getRole(), dbUser.getId());
    }
}
