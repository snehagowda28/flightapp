package com.flightapp.service;

import com.flightapp.domain.Flight;
import com.flightapp.model.SearchFlightDetails;
import com.flightapp.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.flightapp.repository.specification.FlightSearchSpecification.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightService {

    private final FlightRepository flightRepository;

    public void saveFlight(Flight flight) {
        try {
            Flight flightDetails = new Flight();

            flightDetails.setFlightId(flight.getFlightId());
            flightDetails.setBlocked(flight.isBlocked());
            flightDetails.setEndDestination(flight.getEndDestination());
            flightDetails.setStartDestination(flight.getStartDestination());
            flightDetails.setStartTime(flight.getStartTime());
            flightDetails.setEndTime(flight.getEndTime());
            flightDetails.setFlightModel(flight.getFlightModel());
            flightDetails.setFlightCompany(flight.getFlightCompany());

            flightRepository.save(flightDetails);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }

    public Optional<List<Flight>> searchFlight(SearchFlightDetails searchFlightDetails) {
        Specification<Flight> specification = null;

        if(null != searchFlightDetails.getFromDate()){
            specification = fromDate(searchFlightDetails.getFromDate());
        }
        if(null != searchFlightDetails.getToDate()){
            specification = toDate(searchFlightDetails.getToDate()).and(specification);
        }
        if(null != searchFlightDetails.getFromPlace() && !searchFlightDetails.getFromPlace().isEmpty()){
            specification = fromPlace(searchFlightDetails.getFromPlace()).and(specification);
        }
        if(null != searchFlightDetails.getToPlace() && !searchFlightDetails.getToPlace().isEmpty()){
            specification = toPlace(searchFlightDetails.getToPlace()).and(specification);
        }
        return Optional.of(flightRepository.findAll(specification));
    }
}
