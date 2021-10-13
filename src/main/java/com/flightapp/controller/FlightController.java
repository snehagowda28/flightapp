package com.flightapp.controller;

import com.flightapp.domain.Flight;
import com.flightapp.model.FlightBooking;
import com.flightapp.model.SearchFlightDetails;
import com.flightapp.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/airlines")
    public void saveFlight(@RequestBody Flight flight){
        flightService.saveFlight(flight);
    }

    @PostMapping("/search")
    public Optional<List<Flight>> searchFlight(@RequestBody SearchFlightDetails searchFlightDetails){
        return flightService.searchFlight(searchFlightDetails);
    }

//    @PostMapping("/booking/{flightid}")
//    public Optional<List<Flight>> bookFlight(@RequestBody FlightBooking flightBooking){
//        return flightService.searchFlight(searchFlightDetails);
//    }
}
