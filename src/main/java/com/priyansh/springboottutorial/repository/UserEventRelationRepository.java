package com.priyansh.springboottutorial.repository;

import com.priyansh.springboottutorial.Entity.EventDetails;
import com.priyansh.springboottutorial.Entity.UserDetails;
import com.priyansh.springboottutorial.Entity.UserEventRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEventRelationRepository extends JpaRepository<UserEventRelation, Long> {
    Optional<UserEventRelation> findByUserDetailsAndEventDetails(UserDetails userDetails, EventDetails eventDetails);

    List<UserEventRelation> findByUserDetails(UserDetails userDetails);
}
