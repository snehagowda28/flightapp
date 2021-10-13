package com.flightapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFlightDetails {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String fromPlace;
    private String toPlace;
    private boolean isRoundTrip;
}
