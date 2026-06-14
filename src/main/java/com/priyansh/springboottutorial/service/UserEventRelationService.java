package com.priyansh.springboottutorial.service;

import com.priyansh.springboottutorial.Entity.UserEventRelation;
import com.priyansh.springboottutorial.repository.UserEventRelationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEventRelationService {

    private final UserEventRelationRepository userEventRelationRepository;

    public UserEventRelationService(UserEventRelationRepository userEventRelationRepository) {
        this.userEventRelationRepository = userEventRelationRepository;
    }

    public ResponseEntity<List<UserEventRelation>> getAllUserEventRelation() {
        List<UserEventRelation> eventDetailsList  = userEventRelationRepository.findAll();
        return new ResponseEntity<>(eventDetailsList, HttpStatus.OK);
    }
}
