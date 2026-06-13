package com.priyansh.springboottutorial.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "login_details")
public class LoginDetails {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
