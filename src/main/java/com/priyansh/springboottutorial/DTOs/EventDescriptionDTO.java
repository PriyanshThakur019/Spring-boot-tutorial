package com.priyansh.springboottutorial.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDescriptionDTO {
    private int eventId;

    private String eventName;

    private Date eventDate;

    private Time eventTime;

    private int eventDuration;

    private String eventDescription;
}
