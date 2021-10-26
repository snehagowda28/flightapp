package com.flightapp.mapper;

import com.flightapp.domain.FlightBooking;
import com.flightapp.domain.Passenger;
import com.flightapp.openapi.dto.FlightTicket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FlightTicketMapper {

    private final FlightBookingMapper flightBookingMapper;

    public FlightTicket toDto(com.flightapp.domain.FlightBooking flightBooking){
        return FlightTicket
                .builder()
                .pnr(flightBooking.getPnr())
                .flightBooking(flightBookingMapper.toDto(flightBooking))
                .createdAt(flightBooking.getCreatedAt())
                .updatedAt(flightBooking.getUpdatedAt())
                .build();
    }

    public FlightTicket toDtoWithPassenger(FlightBooking flightBooking,
                                           List<Passenger> passengers){
        return FlightTicket
                .builder()
                .pnr(flightBooking.getPnr())
                .flightBooking(flightBookingMapper.toDto(flightBooking))
                .createdAt(flightBooking.getCreatedAt())
                .updatedAt(flightBooking.getUpdatedAt())
                .build();
    }

    public List<FlightTicket> toDtoList(List<FlightBooking> flightBookings){
        return flightBookings.stream()
                .map(fb ->  toDto(fb))
                .collect(Collectors.toList());
    }
}
