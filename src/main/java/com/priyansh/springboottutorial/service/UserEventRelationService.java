package com.priyansh.springboottutorial.service;

import com.priyansh.springboottutorial.DTOs.UserEventRelationDTO;
import com.priyansh.springboottutorial.Entity.EventDetails;
import com.priyansh.springboottutorial.Entity.UserDetails;
import com.priyansh.springboottutorial.Entity.UserEventRelation;
import com.priyansh.springboottutorial.repository.EventDetailsRepository;
import com.priyansh.springboottutorial.repository.UserDetailsRepository;
import com.priyansh.springboottutorial.repository.UserEventRelationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserEventRelationService {

    private final UserEventRelationRepository userEventRelationRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final EventDetailsRepository eventDetailsRepository;

    public UserEventRelationService(UserEventRelationRepository userEventRelationRepository,
                                    UserDetailsRepository userDetailsRepository, EventDetailsRepository eventDetailsRepository) {
        this.userEventRelationRepository = userEventRelationRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.eventDetailsRepository = eventDetailsRepository;
    }

    public ResponseEntity<List<UserEventRelation>> getAllUserEventRelation() {
        List<UserEventRelation> eventDetailsList  = userEventRelationRepository.findAll();
        return new ResponseEntity<>(eventDetailsList, HttpStatus.OK);
    }

    public ResponseEntity<Boolean> registerUserForEvent(UserEventRelationDTO userEventRelationDTO) {
        if(validUserEventRelationRequest(userEventRelationDTO)) {
            log.info("Validation of request done");
            UserDetails user = userDetailsRepository.findByUsername(userEventRelationDTO.getUsername()).orElse(null);
            EventDetails eventDetails = eventDetailsRepository.findByEventName(userEventRelationDTO.getEventName()).orElse(null);

            if (ObjectUtils.isEmpty(user)) {
                log.info("User could not be found and hence registration of the user could not be done");
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
            }

            if (ObjectUtils.isEmpty(eventDetails)) {
                log.info("Event details could not be found and hence registration of the user could not be done");
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.FORBIDDEN);
            }

            LocalDateTime eventDateTime =
                    LocalDateTime.of(eventDetails.getEventDate(), eventDetails.getEventTime());

            boolean isFutureEvent = eventDateTime.isAfter(LocalDateTime.now());

            if (!isFutureEvent) {
                log.info("The given event has closed its registration");
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.FORBIDDEN);
            }

            UserEventRelation userAlreadyRegistered = userEventRelationRepository.findByUserDetailsAndEventDetails(user, eventDetails).orElse(null);
            if (!ObjectUtils.isEmpty(userAlreadyRegistered)) {
                log.info("User already registered for the given event");
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.FORBIDDEN);
            }

            log.info("Registering user for given event");
            UserEventRelation userEventRelation = UserEventRelation.builder()
                    .eventDetails(eventDetails)
                    .userDetails(user)
                    .registeredTime(LocalDateTime.now())
                    .build();

            userEventRelationRepository.saveAndFlush(userEventRelation);

            log.info("Registered user for given Event");

            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }
        else {
            log.info("Validation of request failed");
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validUserEventRelationRequest(UserEventRelationDTO userEventRelationDTO) {
        return !( ObjectUtils.isEmpty(userEventRelationDTO) || ObjectUtils.isEmpty(userEventRelationDTO.getEventName()) ||
                ObjectUtils.isEmpty(userEventRelationDTO.getUsername()) );
    }

}
