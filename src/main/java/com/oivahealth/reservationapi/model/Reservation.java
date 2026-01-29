package com.oivahealth.reservationapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long doctorId;

    private long patientId;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime startDateTime;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime endDateTime;

    private String reason;

    public Reservation(Long doctorId, Long patientId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, String reason) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.reason = reason;
    }
}
