package com.priyansh.springboottutorial.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {
    private int userDetailsId;

    private String username;

    private String name;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;
}
