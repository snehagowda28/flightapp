package com.flightapp.controller;

import com.flightapp.openapi.controller.AirlinesApi;
import com.flightapp.openapi.dto.Flight;
import com.flightapp.openapi.dto.Message;
import com.flightapp.service.AirlinesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AirlinesApiController implements AirlinesApi {

    private final AirlinesService airlinesService;

    @Override
    public ResponseEntity<Flight> addFlights(@Valid Flight flight) {
        return ResponseEntity.ok(airlinesService.addFlights(flight));
    }

    @PutMapping("airlines/status/update")
    public ResponseEntity<Message> updateFlight(@RequestParam Long flightId) {
        return ResponseEntity.ok(airlinesService.updateFlightStatus(flightId));
    }
}
