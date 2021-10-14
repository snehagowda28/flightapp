package com.flightapp.service;

import com.flightapp.mapper.FlightMapper;
import com.flightapp.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

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
}
