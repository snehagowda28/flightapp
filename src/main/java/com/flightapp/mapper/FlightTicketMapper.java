package com.flightapp.mapper;

import com.flightapp.openapi.dto.FlightTicket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
