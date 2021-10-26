package com.flightapp.service;

import com.flightapp.domain.Flight;
import com.flightapp.mapper.FlightBookingMapper;
import com.flightapp.mapper.FlightMapper;
import com.flightapp.mapper.FlightTicketMapper;
import com.flightapp.openapi.dto.*;
import com.flightapp.repository.FlightBookingRepository;
import com.flightapp.repository.FlightRepository;
import com.flightapp.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.flightapp.repository.specification.FlightSearchSpecification.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightsService {

    private final FlightBookingRepository flightBookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
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
            specification = fromPlace(flightsSearchByOptions.getStartDestination()).and(specification);
        }
        if(null != flightsSearchByOptions.getEndDestination() && !flightsSearchByOptions.getEndDestination().isEmpty()){
            specification = toPlace(flightsSearchByOptions.getEndDestination()).and(specification);
        }
        if(flightRepository.findAll(specification).isEmpty()){
            return Flights.builder().build();
        }else{
            specification = isNotBlocked().and(specification);
            return flightMapper.toDtoList(flightRepository.findAll(specification));
        }
    }

    public FlightBookingResponse addFlightBooking(Long flightId, FlightBooking flightBooking) {
        Flight flight = flightRepository.getById(flightId);
        return flightBookingMapper.toDtoWithPNR(flightBookingRepository.save(flightBookingMapper.toDomain(flight, flightBooking)));
    }

    public FlightTickets getFlightTicketByEmail(String emailId) {
        if(!emailId.isEmpty()){
            return FlightTickets
                    .builder()
                    .flights(flightTicketMapper.toDtoList(flightBookingRepository.findByEmailIgnoreCase(emailId)))
                    .build();
        }
        return null;
    }

    public FlightTicket getFlightTicketByPNR(String pnr) {
        if(!pnr.isEmpty()){
            return flightTicketMapper.toDtoWithPassenger(flightBookingRepository.findByPnr(pnr),
                    passengerRepository.findByPNR(pnr));
        }
        return null;
    }

    public Message cancelBooking(String pnr) {
        if(!pnr.isEmpty()){
            com.flightapp.domain.FlightBooking flightBooking = flightBookingRepository.findByPnr(pnr);
            Flight flight = flightRepository.getById(flightBooking.getFlight().getFlightId());
            if(ChronoUnit.HOURS.between(LocalDateTime.now(), flight.getStartTime()) >= 24){
                flightRepository.delete(flight);
                return Message
                        .builder()
                        .source("FLIGHT_APP")
                        .reasonCode("DELETED")
                        .description("CANCELLATION DONE SUCCESSFULLY")
                        .build();
            }else{
                return Message
                        .builder()
                        .source("FLIGHT_APP")
                        .reasonCode("FLIGHT TIME SHOULD BE LESS THAN 24HRS")
                        .description("CANCELLATION SHOULD BE DONE 24HRS BEFORE THE FLIGHT TIME")
                        .build();
            }
        }
        return Message
                .builder()
                .source("FLIGHT_APP")
                .reasonCode("BOOKING DOES NOT EXIST")
                .description("CANCELLATION CANNOT BE DONE IF NO BOOKING IS DONE")
                .build();
    }
}
