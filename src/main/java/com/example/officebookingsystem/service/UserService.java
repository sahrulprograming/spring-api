package com.example.officebookingsystem.service;

import com.example.officebookingsystem.domain.entity.User;
import com.example.officebookingsystem.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserByEmail(String email){
            return userRepository.findUserByEmail(email);
    }
}
