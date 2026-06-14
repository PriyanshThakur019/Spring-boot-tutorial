package com.priyansh.springboottutorial.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDetailsDTO {
    private Integer eventId;

    private String eventName;

    private LocalDate eventDate;

    private LocalTime eventTime;

    private Integer eventDuration;

    private String eventDescription;
}
