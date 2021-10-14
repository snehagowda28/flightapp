package com.flightapp.repository;

import com.flightapp.domain.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {
    FlightBooking findByEmailIgnoreCase(String email);
    FlightBooking findByPnr(String pnr);
}
