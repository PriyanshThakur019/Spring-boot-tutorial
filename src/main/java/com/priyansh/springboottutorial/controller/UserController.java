package com.priyansh.springboottutorial.controller;

import com.priyansh.springboottutorial.DTOs.UserDetailsDTO;
import com.priyansh.springboottutorial.Entity.UserDetails;
import com.priyansh.springboottutorial.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUserDetails")
    public ResponseEntity<List<UserDetails>> getAllLoginDetails() {
        return userService.fetchAllUserDetails();
    }

    @PostMapping("/registerUser")
    public ResponseEntity<Boolean> registerUser(@RequestBody UserDetailsDTO userDetailsDTO) {
        return userService.registerUser(userDetailsDTO);
    }
}
