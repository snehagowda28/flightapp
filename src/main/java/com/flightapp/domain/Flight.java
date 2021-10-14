package com.flightapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Flight {

    @Id
    @Column(name = "FLIGHT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long flightId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEAL_ID", nullable = true)
    private Meal meal;

    @Column
    private Long price;

    @Column
    private com.flightapp.openapi.dto.Flight.FlightCompanyEnum flightCompany;

    @Column
    private String flightModel;

    @Column
    private String startDestination;

    @Column
    private String endDestination;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private boolean isBlocked;
}
