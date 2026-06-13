package com.priyansh.springboottutorial.service;

import com.priyansh.springboottutorial.Entity.UserDetails;
import com.priyansh.springboottutorial.repository.UserDetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDetailsRepository userDetailsRepository;

    public UserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public ResponseEntity<List<UserDetails>> fetchAllUserDetails() {
        List<UserDetails> userDetailsList = userDetailsRepository.findAll();
        return new ResponseEntity<>(userDetailsList, HttpStatus.OK);
    }
}
