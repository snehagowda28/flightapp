package com.flightapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Flight {

    @Id
    @Column(name = "FLIGHT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int flightId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEAL_ID", referencedColumnName = "MEAL_ID")
    private Meal mealId;

    @Column
    private Long price;

    @Column
    private FlightCompany flightCompany;

    @Column
    private String flightModel;

    @Column
    private String startDestination;

    @Column
    private String endDestination;

    @Column
    private LocalDate startTime;

    @Column
    private LocalDate endTime;

    @Column
    private boolean isBlocked;
}
