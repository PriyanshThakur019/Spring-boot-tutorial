package com.priyansh.springboottutorial.service;

import com.priyansh.springboottutorial.DTOs.CredentialsDTO;
import com.priyansh.springboottutorial.Entity.LoginDetails;
import com.priyansh.springboottutorial.repository.LoginDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
public class LoginService {

    private final LoginDetailsRepository loginDetailsRepository;

    public LoginService(LoginDetailsRepository loginDetailsRepository) {
        this.loginDetailsRepository = loginDetailsRepository;
    }

    public ResponseEntity<Boolean> loginWithUserNameAndPassword(CredentialsDTO credentialsDTO) {
        LoginDetails user = loginDetailsRepository.findByUsernameAndPassword(credentialsDTO.getUsername(), credentialsDTO.getPassword()).orElse(null);

        if (ObjectUtils.isEmpty(user)) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    public ResponseEntity<List<LoginDetails>> fetchAllUserDetails() {

        List<LoginDetails> users = loginDetailsRepository.findAll();
        if (!CollectionUtils.isEmpty(users)) {
            for(LoginDetails user : users) {
                log.info("username is {} and pass is {}", user.getUsername(), user.getPassword());
            }
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public boolean checkIfUserNameExist(String username) {
        LoginDetails user = loginDetailsRepository.findByUsername(username).orElse(null);
        return !ObjectUtils.isEmpty(user);
    }

    public void insertLoginDetails(String username, String password) {
        boolean userExist = checkIfUserNameExist(username);

        if (userExist) {
            log.info("This username is not available");
            return;
        }

        LoginDetails loginDetails = LoginDetails.builder()
                .username(username)
                .password(password)
                .build();

        loginDetailsRepository.save(loginDetails);

        log.info("SAVED logindetails");
    }
}
