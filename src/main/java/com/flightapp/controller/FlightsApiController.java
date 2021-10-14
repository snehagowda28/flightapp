package com.flightapp.controller;

import com.flightapp.openapi.controller.FlightsApi;
import com.flightapp.openapi.dto.Flight;
import com.flightapp.openapi.dto.FlightBooking;
import com.flightapp.openapi.dto.FlightTicket;
import com.flightapp.openapi.dto.Flights;
import com.flightapp.openapi.dto.FlightsSearchByOptions;
import com.flightapp.service.FlightsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FlightsApiController implements FlightsApi {

    private final FlightsService flightsService;

    @Override
    public ResponseEntity<FlightBooking> addFlightBooking(Long flightId, @Valid FlightBooking flightBooking) {
        return ResponseEntity.ok(flightsService.addFlightBooking(flightId, flightBooking));
    }

    @Override
    public ResponseEntity<Void> cancelBooking(String pnr) {
        return null;
    }

    @Override
    public ResponseEntity<FlightTicket> getFlightTicketByEmail(String emailId) {
        return ResponseEntity.ok(flightsService.getFlightTicketByEmail(emailId));
    }

    @Override
    public ResponseEntity<FlightTicket> getFlightTicketByPNR(String pnr) {
        return ResponseEntity.ok(flightsService.getFlightTicketByPNR(pnr));
    }

    @Override
    public ResponseEntity<Flights> getFlightsDetail(@Valid FlightsSearchByOptions flightsSearchByOptions) {
        return ResponseEntity.ok(flightsService.getFlightsDetail(flightsSearchByOptions));
    }
}
