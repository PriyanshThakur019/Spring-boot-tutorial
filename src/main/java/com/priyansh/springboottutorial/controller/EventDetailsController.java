package com.priyansh.springboottutorial.controller;

import com.priyansh.springboottutorial.DTOs.EventDetailsDTO;
import com.priyansh.springboottutorial.Entity.EventDetails;
import com.priyansh.springboottutorial.service.EventDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class EventDetailsController {
    private final EventDetailsService eventDetailsService;

    public EventDetailsController(EventDetailsService eventDetailsService) {
        this.eventDetailsService = eventDetailsService;
    }

    @GetMapping("/getAllEventDetails")
    public ResponseEntity<List<EventDetails>> getAllEventDetails() {
        return eventDetailsService.getAllEventDetails();
    }

    @PostMapping("/registerEvent")
    public ResponseEntity<Boolean> registerEvent(@RequestBody EventDetailsDTO eventDetailsDTO) {
        log.info("Starting to register the event");
        return eventDetailsService.registerEvent(eventDetailsDTO);
    }

    @GetMapping("/getFutureEventsList")
    public List<EventDetails> getAllFutureEvents() {
        return eventDetailsService.getAllFutureEvents();
    }

    @GetMapping("/getPastEventsList")
    public List<EventDetails> getAllPastEvents() {
        return eventDetailsService.getAllPastEvents();
    }
}
