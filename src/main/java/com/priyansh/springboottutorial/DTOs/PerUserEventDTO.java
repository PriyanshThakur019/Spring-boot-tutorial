package com.priyansh.springboottutorial.DTOs;

import com.priyansh.springboottutorial.Entity.EventDetails;
import com.priyansh.springboottutorial.Entity.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerUserEventDTO {
    private UserDetails user;
    private List<EventDetails> pastEventDetailsList;
    private List<EventDetails> futureEventDetailsList;
}
