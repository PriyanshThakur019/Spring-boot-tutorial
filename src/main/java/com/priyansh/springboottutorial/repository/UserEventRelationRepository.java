package com.priyansh.springboottutorial.repository;

import com.priyansh.springboottutorial.Entity.UserEventRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventRelationRepository extends JpaRepository<UserEventRelation, Long> {
}
