package com.priyansh.springboottutorial.repository;

import com.priyansh.springboottutorial.Entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LoginDetailsRepository extends JpaRepository<LoginDetails, String> {

    Optional<LoginDetails> findByUsername(String username);

    Optional<LoginDetails> findByUsernameAndPassword(String userName, String password);
}