package com.priyansh.springboottutorial.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEventRelationDTO {
    private Integer userEventRelationId;

    private String username;

    private String eventName;

    private Integer eventId;

    private LocalDateTime registeredTime;
}
