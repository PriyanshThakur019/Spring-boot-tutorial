package com.priyansh.springboottutorial.controller;

import com.priyansh.springboottutorial.Entity.UserEventRelation;
import com.priyansh.springboottutorial.service.UserEventRelationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserEventRelationController {
    private final UserEventRelationService userEventRelationService;

    public UserEventRelationController(UserEventRelationService userEventRelationService) {
        this.userEventRelationService = userEventRelationService;
    }

    @GetMapping("/getAllUserEventRelation")
    public ResponseEntity<List<UserEventRelation>> getAllUserEventRelation() {
        return userEventRelationService.getAllUserEventRelation();
    }
}
