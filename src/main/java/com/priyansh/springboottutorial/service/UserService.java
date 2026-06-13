package com.priyansh.springboottutorial.service;

import com.priyansh.springboottutorial.DTOs.UserDetailsDTO;
import com.priyansh.springboottutorial.Entity.UserDetails;
import com.priyansh.springboottutorial.repository.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserDetailsRepository userDetailsRepository;
    private final LoginService loginService;

    public UserService(UserDetailsRepository userDetailsRepository, LoginService loginService) {
        this.userDetailsRepository = userDetailsRepository;
        this.loginService = loginService;
    }

    public ResponseEntity<List<UserDetails>> fetchAllUserDetails() {
        List<UserDetails> userDetailsList = userDetailsRepository.findAll();
        return new ResponseEntity<>(userDetailsList, HttpStatus.OK);
    }

    public ResponseEntity<Boolean> registerUser(UserDetailsDTO userDetailsDTO) {
        boolean userExist = loginService.checkIfUserNameExist(userDetailsDTO.getUsername());
        boolean phoneNumberExist = userDetailsRepository.countByPhoneNumber(userDetailsDTO.getPhoneNumber())>0;

        if (userExist) {
            log.info("This username is not available");
            return new ResponseEntity<>(false, HttpStatus.IM_USED);
        }

        if (phoneNumberExist) {
            log.info("This number already exist");
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }
        try {
            loginService.insertLoginDetails(userDetailsDTO.getUsername(), userDetailsDTO.getPassword());

            UserDetails userDetails = UserDetails.builder()
                    .email(userDetailsDTO.getEmail())
                    .name(userDetailsDTO.getName())
                    .phoneNumber(userDetailsDTO.getPhoneNumber())
                    .username(userDetailsDTO.getUsername())
                    .build();

            userDetailsRepository.save(userDetails);
            log.info("SAVED user details");
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Couldn't register the user");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }

    }
}
