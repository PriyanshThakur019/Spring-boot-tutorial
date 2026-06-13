package com.priyansh.springboottutorial.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "login_details")
public class LoginDetails {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
