package com.priyansh.springboottutorial.repository;

import com.priyansh.springboottutorial.Entity.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDetailsRepository extends JpaRepository<EventDetails, Long> {
}
