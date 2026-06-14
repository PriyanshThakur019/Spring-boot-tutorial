package com.priyansh.springboottutorial.repository;

import com.priyansh.springboottutorial.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    @Query(
            value = "SELECT COUNT(*) FROM user_details WHERE phone_number = :phoneNumber",
            nativeQuery = true
    )
    Long countByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    Optional<UserDetails> findByUsername(String username);
}
