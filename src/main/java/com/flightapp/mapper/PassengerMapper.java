package com.flightapp.mapper;


import com.flightapp.openapi.dto.FlightBooking;
import com.flightapp.openapi.dto.FlightBookingPassengers;
import com.flightapp.openapi.dto.Passenger;
import com.flightapp.utils.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PassengerMapper {

    private final GenericMapper genericMapper;

    public Passenger toDto(com.flightapp.domain.Passenger passenger) {
        return Passenger
                .builder()
                .name(passenger.getPassengerName())
                .age(passenger.getAge())
                .gender(passenger.getGender())
                .build();
    }

    public com.flightapp.domain.Passenger toDomain(Passenger passenger) {
        return com.flightapp.domain.Passenger
                .builder()
                .passengerName(passenger.getName())
                .age(passenger.getAge())
                .gender(passenger.getGender())
                .build();
    }

    public List<FlightBookingPassengers> toDtoList(com.flightapp.domain.FlightBooking flightBooking) {
        return flightBooking.getPassengers().stream()
                .map(p -> {
                    return FlightBookingPassengers
                            .builder()
                            .passenger(toDto(p))
                            .isNonVegMeal(p.isNonVegMeal())
                            .build();
                }).collect(Collectors.toList());
    }

    public List<com.flightapp.domain.Passenger> toDomainList(FlightBooking flightBooking) {
        return flightBooking.getPassengers().stream()
                .map(p -> {
                    return com.flightapp.domain.Passenger
                            .builder()
                            .passengerName(p.getPassenger().getName())
                            .age(p.getPassenger().getAge())
                            .gender(p.getPassenger().getGender())
                            .isNonVegMeal(p.getIsNonVegMeal())
                            .build();
                }).collect(Collectors.toList());
    }
}
