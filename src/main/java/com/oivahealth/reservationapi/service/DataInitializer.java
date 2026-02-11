package com.oivahealth.reservationapi.service;

import com.oivahealth.reservationapi.model.Reservation;
import com.oivahealth.reservationapi.model.User;
import com.oivahealth.reservationapi.repository.ReservationRepository;
import com.oivahealth.reservationapi.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public DataInitializer(ReservationRepository reservationRepository,
                           UserRepository userRepository,
                           PasswordEncoder encoder) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String @NonNull ... args) {
        ZonedDateTime now = ZonedDateTime.now().withMinute(0).withSecond(0).withNano(0);
        if (userRepository.count() == 0) {
            User aino = userRepository.save(new User("aino", encoder.encode("pass"), User.Role.ROLE_CUSTOMER));
            User matti = userRepository.save(new User("matti", encoder.encode("pass"), User.Role.ROLE_CUSTOMER));
            User eevi = userRepository.save(new User("eevi", encoder.encode("pass"), User.Role.ROLE_DOCTOR));
            reservationRepository.save(new Reservation(eevi.getId(), aino.getId(), now, now.plusMinutes(30), "Health check"));
            reservationRepository.save(new Reservation(eevi.getId(), matti.getId(), now.plusMinutes(30), now.plusHours(1), "Sore throat"));
        }
    }
}

