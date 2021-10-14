package com.flightapp.service;

import com.flightapp.domain.Flight;
import com.flightapp.mapper.FlightBookingMapper;
import com.flightapp.mapper.FlightMapper;
import com.flightapp.mapper.FlightTicketMapper;
import com.flightapp.openapi.dto.FlightBooking;
import com.flightapp.openapi.dto.FlightTicket;
import com.flightapp.openapi.dto.Flights;
import com.flightapp.openapi.dto.FlightsSearchByOptions;
import com.flightapp.repository.FlightBookingRepository;
import com.flightapp.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.flightapp.repository.specification.FlightSearchSpecification.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightsService {

    private final FlightBookingRepository flightBookingRepository;
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final FlightTicketMapper flightTicketMapper;
    private final FlightBookingMapper flightBookingMapper;

    public Flights getFlightsDetail(FlightsSearchByOptions flightsSearchByOptions) {
        Specification<Flight> specification = null;

        if(null != flightsSearchByOptions.getFromDate()){
            specification = fromDate(flightsSearchByOptions.getFromDate());
        }
        if(null != flightsSearchByOptions.getToDate()){
            specification = toDate(flightsSearchByOptions.getToDate()).and(specification);
        }
        if(null != flightsSearchByOptions.getStartDestination() && !flightsSearchByOptions.getStartDestination().isEmpty()){
            specification = fromPlace(flightsSearchByOptions.getEndDestination()).and(specification);
        }
        if(null != flightsSearchByOptions.getEndDestination() && !flightsSearchByOptions.getEndDestination().isEmpty()){
            specification = toPlace(flightsSearchByOptions.getEndDestination()).and(specification);
        }
        if(flightRepository.findAll(specification).isEmpty()){
            return null;
        }else{
            return flightMapper.toDtoList(flightRepository.findAll(specification));
        }
    }

    public FlightBooking addFlightBooking(Long flightId, FlightBooking flightBooking) {
        try{
            Flight flight = flightRepository.getById(flightId);
            return flightBookingMapper.toDto(flightBookingRepository.save(flightBookingMapper.toDomain(flightBooking)));
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            throw new EntityNotFoundException("Flight Id Not found " + flightId);
        }
    }

    public FlightTicket getFlightTicketByEmail(String emailId) {
        if(!emailId.isEmpty()){
            return flightTicketMapper.toDto(flightBookingRepository.findByEmailIgnoreCase(emailId));
        }
        return null;
    }

    public FlightTicket getFlightTicketByPNR(String pnr) {
        if(!pnr.isEmpty()){
            return flightTicketMapper.toDto(flightBookingRepository.findByPnr(pnr));
        }
        return null;
    }
}
