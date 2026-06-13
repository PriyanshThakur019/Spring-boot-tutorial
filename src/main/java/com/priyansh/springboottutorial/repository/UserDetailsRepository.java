package com.priyansh.springboottutorial.repository;

import com.priyansh.springboottutorial.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

}
