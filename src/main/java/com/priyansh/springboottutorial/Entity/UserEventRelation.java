package com.priyansh.springboottutorial.Entity;

import com.priyansh.springboottutorial.DTOs.UserDetailsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_event_relation")
public class UserEventRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_event_relation_id")
    private int userEventRelationId;

    @Column(name = "username")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private UserDetailsDTO userDetailsDTO;

    @Column(name = "event_id")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private EventDetails eventDetails;

    @Column(name = "registered_time")
    private LocalDateTime registeredTime;
}
