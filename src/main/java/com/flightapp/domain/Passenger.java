package com.flightapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Passenger {
    @Id
    @Column(name = "PASSENGER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long passengerId;

    @ManyToOne
    @JoinColumn(name = "PNR")
    private FlightBooking flightBooking;

//    @ManyToOne
//    @JoinColumn(name = "MEAL_ID")
//    private Meal meal;

    @Column
    private boolean isNonVegMeal;

    @Column
    private String passengerName;

    @Column
    private com.flightapp.openapi.dto.Passenger.GenderEnum gender;

    @Column
    private Integer age;
}
