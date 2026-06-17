package com.priyansh.springboottutorial.controller;

import com.priyansh.springboottutorial.DTOs.CredentialsDTO;
import com.priyansh.springboottutorial.Entity.LoginDetails;
import com.priyansh.springboottutorial.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@CrossOrigin
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody CredentialsDTO credentialsDTO) {
        return loginService.loginWithUserNameAndPassword(credentialsDTO);
    }

    @GetMapping("/getAllLoginDetails")
    public ResponseEntity<List<LoginDetails>> getAllLoginDetails() {
        return loginService.fetchAllUserDetails();
    }

    @GetMapping("/checkIfUserNameExist")
    public ResponseEntity<Boolean> checkIfUserNameExist(@RequestParam String username) {
        return new ResponseEntity<>(loginService.checkIfUserNameExist(username), HttpStatus.OK);
    }

}

