package com.nirbhay.rideSharingApp.service;

import com.nirbhay.rideSharingApp.model.User;
import com.nirbhay.rideSharingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) throw new UsernameNotFoundException("User not found" + username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }

    public User loadByUsernameSimple(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User register(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole(role);
        return userRepository.save(user);
    }
}
