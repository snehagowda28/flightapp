package com.flightapp.service;

import com.flightapp.mapper.FlightMapper;
import com.flightapp.openapi.dto.Message;
import com.flightapp.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AirlinesService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public com.flightapp.openapi.dto.Flight addFlights(com.flightapp.openapi.dto.@Valid Flight flight) {
        try {
            return flightMapper.toDto(flightRepository.save(flightMapper.toDomain(flight)));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    public Message updateFlightStatus(Long flightId) {
        Optional<com.flightapp.domain.Flight> flight = flightRepository.findById(flightId);

        if (null != flight.get()) {
            flight.ifPresent(f -> {
                f.setBlocked(!f.isBlocked());
                flightRepository.save(f);
            });
            return Message
                    .builder()
                    .source("FLIGHT_APP")
                    .reasonCode("FLIGHT_ID_STATUS_UPDATED")
                    .description("FLIGHT ID FOUND AND STATUS IS UPDATED")
                    .build();
        }else{
            return Message
                    .builder()
                    .source("FLIGHT_APP")
                    .reasonCode("FLIGHT_ID_NOT_FOUND")
                    .description("NO FLIGHT ID FOUND FOR GIVEN INPUT")
                    .build();
        }
    }
}
