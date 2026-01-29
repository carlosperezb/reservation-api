package com.oivahealth.reservationapi.controller;

import com.oivahealth.reservationapi.model.Reservation;
import com.oivahealth.reservationapi.repository.ReservationRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ReservationController {

    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
      this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        try {
            List<Reservation> reservations = reservationRepository.findAll();
            if (reservations.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
