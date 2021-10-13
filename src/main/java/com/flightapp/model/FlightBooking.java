package com.flightapp.model;

import com.flightapp.domain.Meal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightBooking {
    private int flightNumber;
    private LocalDate flightTime;
    private String fromPlace;
    private String toPlace;
    private List User;
    private Meal meal;
}
