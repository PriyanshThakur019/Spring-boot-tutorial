package com.priyansh.springboottutorial.controller;

import com.priyansh.springboottutorial.DTOs.PerUserEventDTO;
import com.priyansh.springboottutorial.DTOs.UserEventRelationDTO;
import com.priyansh.springboottutorial.Entity.EventDetails;
import com.priyansh.springboottutorial.Entity.UserEventRelation;
import com.priyansh.springboottutorial.service.UserEventRelationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class UserEventRelationController {
    private final UserEventRelationService userEventRelationService;

    public UserEventRelationController(UserEventRelationService userEventRelationService) {
        this.userEventRelationService = userEventRelationService;
    }

    @GetMapping("/getAllUserEventRelation")
    public ResponseEntity<List<UserEventRelation>> getAllUserEventRelation() {
        return userEventRelationService.getAllUserEventRelation();
    }

    @GetMapping("/getAllEventsPerUser")
    public PerUserEventDTO getAllEventsPerUser(@RequestParam String username) {
        return userEventRelationService.getAllEventsPerUser(username);
    }

    @PostMapping("/registerUserForEvent")
    public ResponseEntity<Boolean> registerUserForEvent(@RequestBody UserEventRelationDTO userEventRelationDTO) {
        return userEventRelationService.registerUserForEvent(userEventRelationDTO);
    }
}
