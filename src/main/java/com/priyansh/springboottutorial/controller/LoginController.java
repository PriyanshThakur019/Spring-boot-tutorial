package com.priyansh.springboottutorial.controller;

import com.priyansh.springboottutorial.DTOs.CredentialsDTO;
import com.priyansh.springboottutorial.Entity.LoginDetails;
import com.priyansh.springboottutorial.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialsDTO credentialsDTO) {
        return loginService.loginWithUserNameAndPassword(credentialsDTO);
    }

    @GetMapping("/getAllLoginDetails")
    public ResponseEntity<List<LoginDetails>> getAllLoginDetails() {
        return loginService.fetchAllUserDetails();
    }
}
