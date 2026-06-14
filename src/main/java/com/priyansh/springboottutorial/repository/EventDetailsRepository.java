package com.priyansh.springboottutorial.repository;

import com.priyansh.springboottutorial.Entity.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventDetailsRepository extends JpaRepository<EventDetails, Long> {
    Optional<EventDetails> findByEventName(String eventName);
}
