package com.flightapp.repository.specification;

import com.flightapp.domain.Flight;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FlightSearchSpecification {

    public static Specification<Flight> fromDate(LocalDate fromDate){
        return (flight, cq, cb) -> cb.greaterThanOrEqualTo(flight.<LocalDate>get("startTime"), fromDate);
    }

    public static Specification<Flight> toDate(LocalDate toDate){
        return (flight, cq, cb) -> cb.lessThanOrEqualTo(flight.<LocalDate>get("endTime"), toDate);
    }

    public static Specification<Flight> fromPlace(String fromPlace){
        return (flight, cq, cb) -> cb.equal(flight.get("startDestination"), fromPlace);
    }

    public static Specification<Flight> toPlace(String toPlace){
        return (flight, cq, cb) -> cb.equal(flight.get("endDestination"), toPlace);
    }
}
