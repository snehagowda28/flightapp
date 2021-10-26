package com.flightapp.repository.specification;

import com.flightapp.domain.Flight;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FlightSearchSpecification {

    public static Specification<Flight> fromDate(LocalDate fromDate){
        return (flight, cq, cb) -> cb.greaterThanOrEqualTo(flight.<LocalDateTime>get("startTime"), fromDate.atStartOfDay());
    }

    public static Specification<Flight> toDate(LocalDate toDate){
        return (flight, cq, cb) -> cb.lessThanOrEqualTo(flight.<LocalDateTime>get("endTime"), toDate.atStartOfDay());
    }

    public static Specification<Flight> fromPlace(String fromPlace){
        return (flight, cq, cb) -> cb.equal(flight.get("startDestination"), fromPlace);
    }

    public static Specification<Flight> toPlace(String toPlace){
        return (flight, cq, cb) -> cb.equal(flight.get("endDestination"), toPlace);
    }

    public static Specification<Flight> isNotBlocked(){
        return (flight, cq, cb) -> cb.isFalse(flight.get("isBlocked"));
    }
}
