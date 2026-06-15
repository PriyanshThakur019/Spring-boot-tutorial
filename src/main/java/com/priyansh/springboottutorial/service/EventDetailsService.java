package com.priyansh.springboottutorial.service;

import com.priyansh.springboottutorial.DTOs.EventDetailsDTO;
import com.priyansh.springboottutorial.Entity.EventDetails;
import com.priyansh.springboottutorial.repository.EventDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EventDetailsService {

    private final EventDetailsRepository eventDetailsRepository;

    public EventDetailsService(EventDetailsRepository eventDetailsRepository) {
        this.eventDetailsRepository = eventDetailsRepository;
    }

    public ResponseEntity<List<EventDetails>> getAllEventDetails() {
        List<EventDetails> eventDetailsList  = eventDetailsRepository.findAll();
        return new ResponseEntity<>(eventDetailsList, HttpStatus.OK);
    }

    public ResponseEntity<Boolean> registerEvent(EventDetailsDTO eventDetailsDTO) {
        log.info("Image time is "+eventDetailsDTO.getEventDuration());
        log.info("Image url is "+eventDetailsDTO.getImageUrl());
        try {
            if(eventRegistrationRequestIsValid(eventDetailsDTO)) {
                EventDetails eventDetails = EventDetails.builder()
                        .eventDate(eventDetailsDTO.getEventDate())
                        .eventDescription(eventDetailsDTO.getEventDescription())
                        .eventName(eventDetailsDTO.getEventName())
                        .eventTime(eventDetailsDTO.getEventTime())
                        .eventDuration(eventDetailsDTO.getEventDuration())
                        .imageUrl(eventDetailsDTO.getImageUrl())
                        .build();
                log.info("validation successful now starting to register the event in database");
                eventDetailsRepository.saveAndFlush(eventDetails);
                log.info("Event registered succesfully on DB");
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else {
                log.info("Event registration failed");
                log.info("Insufficient/incorrect information for registration of event given, please give the full registration information");
                return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            log.info("Found some unexpected error on trying to register the event");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_CONTENT);
        }

    }

    private boolean eventRegistrationRequestIsValid(EventDetailsDTO eventDetailsDTO) {
        log.info("event registration validation started");
        return !(ObjectUtils.isEmpty(eventDetailsDTO) || ObjectUtils.isEmpty(eventDetailsDTO.getEventDuration()) ||
                ObjectUtils.isEmpty(eventDetailsDTO.getEventName()) || ObjectUtils.isEmpty(eventDetailsDTO.getEventDescription()) ||
                ObjectUtils.isEmpty(eventDetailsDTO.getEventTime()));
    }

    public List<EventDetails> getAllFutureEvents() {
        List<EventDetails> futureEventList = new ArrayList<>();

        List<EventDetails> eventDetailsList = getAllEventDetails().getBody();

        if (!CollectionUtils.isEmpty(eventDetailsList)) {
            for(EventDetails eventDetails: eventDetailsList) {
                if(!ObjectUtils.isEmpty(eventDetails)) {
                    LocalDateTime eventDateTime= LocalDateTime.of(eventDetails.getEventDate(), eventDetails.getEventTime());
                    if (eventDateTime.isAfter(LocalDateTime.now())) {
                        futureEventList.add(eventDetails);
                    }
                }
            }
        }

        return futureEventList;
    }

    public List<EventDetails> getAllPastEvents() {
        List<EventDetails> pastEventList = new ArrayList<>();

        List<EventDetails> eventDetailsList = getAllEventDetails().getBody();

        if (!CollectionUtils.isEmpty(eventDetailsList)) {
            for(EventDetails eventDetails: eventDetailsList) {
                if(!ObjectUtils.isEmpty(eventDetails)) {
                    LocalDateTime eventDateTime= LocalDateTime.of(eventDetails.getEventDate(), eventDetails.getEventTime());
                    if (eventDateTime.isBefore(LocalDateTime.now())) {
                        pastEventList.add(eventDetails);
                    }
                }
            }
        }

        return pastEventList;
    }
}
