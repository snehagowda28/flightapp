package com.flightapp.domain;

import lombok.*;

import javax.persistence.*;

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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PNR", referencedColumnName = "PNR")
//    private FlightBooking flightBooking;

    @Column
    private boolean isNonVegMeal;

    @Column
    private String passengerName;

    @Column
    private com.flightapp.openapi.dto.Passenger.GenderEnum gender;

    @Column
    private Integer age;
}
