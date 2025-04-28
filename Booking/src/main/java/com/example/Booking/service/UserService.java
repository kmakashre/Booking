package com.example.Booking.service;

import com.example.Booking.model.User;
import com.example.Booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Add service methods, e.g., to save a user
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}