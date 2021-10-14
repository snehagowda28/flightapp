package com.flightapp.mapper;

import com.flightapp.openapi.dto.Flight;
import com.flightapp.openapi.dto.Flights;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FlightMapper {
    private final MealMapper mealMapper;

    public Flight toDto(com.flightapp.domain.Flight flight) {
        return Flight
                .builder()
                .flightId(flight.getFlightId())
                .meal(mealMapper.toDto(flight.getMeal()))
                .price(flight.getPrice())
                .flightCompany(flight.getFlightCompany())
                .flightModel(flight.getFlightModel())
                .startDestination(flight.getStartDestination())
                .endDestination(flight.getEndDestination())
                .startTime(flight.getStartTime())
                .endTime(flight.getEndTime())
                .isBlocked(flight.isBlocked())
                .build();
    }

    public Flights toDtoList(List<com.flightapp.domain.Flight> flights) {
        return Flights
                .builder()
                .flights(flights.stream().map(this::toDto).collect(Collectors.toList()))
                .build();
    }

    public com.flightapp.domain.Flight toDomain(Flight flight) {
        return com.flightapp.domain.Flight
                .builder()
                .flightId(flight.getFlightId())
                .meal(mealMapper.toDomain(flight.getMeal()))
                .price(flight.getPrice())
                .flightCompany(flight.getFlightCompany())
                .flightModel(flight.getFlightModel())
                .startDestination(flight.getStartDestination())
                .endDestination(flight.getEndDestination())
                .startTime(flight.getStartTime())
                .endTime(flight.getEndTime())
                .isBlocked(flight.getIsBlocked())
                .build();
    }
}
