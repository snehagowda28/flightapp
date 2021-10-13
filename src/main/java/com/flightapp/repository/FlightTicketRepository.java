package com.flightapp.repository;

import com.flightapp.domain.FlightTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightTicketRepository extends JpaRepository<FlightTicket, Integer> {
}
