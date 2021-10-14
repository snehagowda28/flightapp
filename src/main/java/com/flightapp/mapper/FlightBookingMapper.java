package com.flightapp.mapper;

import com.flightapp.domain.Flight;
import com.flightapp.openapi.dto.FlightBooking;
import com.flightapp.openapi.dto.FlightBookingPassengers;
import com.flightapp.openapi.dto.Passenger;
import com.flightapp.utils.GeneratePNR;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class FlightBookingMapper {

    private final PassengerMapper passengerMapper;
    private final GeneratePNR generatePNR;

    public FlightBooking toDto(com.flightapp.domain.FlightBooking flightBooking){
        return FlightBooking
                .builder()
                .date(flightBooking.getDate())
                .startDestination(flightBooking.getStartDestination())
                .endDestination(flightBooking.getEndDestination())
                .name(flightBooking.getName())
                .email(flightBooking.getEmail())
                .seats(flightBooking.getSeats())
                .passengers(passengerMapper.toDtoList(flightBooking))
                .build();
    }

    public com.flightapp.domain.FlightBooking toDomain(FlightBooking flightBooking){
        return com.flightapp.domain.FlightBooking
                .builder()
                .pnr(generatePNR.randomPNR())
                .date(flightBooking.getDate())
                .startDestination(flightBooking.getStartDestination())
                .endDestination(flightBooking.getEndDestination())
                .name(flightBooking.getName())
                .email(flightBooking.getEmail())
                .seats(flightBooking.getSeats())
                .passengers(passengerMapper.toDomainList(flightBooking))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
