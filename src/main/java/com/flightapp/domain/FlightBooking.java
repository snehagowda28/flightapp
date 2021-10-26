package com.flightapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class FlightBooking {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "PNR")
    private String pnr;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FLIGHT_ID", nullable = false)
    private Flight flight;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FLIGHT_ID", nullable = false)
    private List<Passenger> passengers;

//    @OneToMany(mappedBy = "flightBooking", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Passenger> passengers = new ArrayList<>();

    @Column
    private LocalDateTime date;

    @Column
    private String startDestination;

    @Column
    private String endDestination;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private Long seats;

    @Column
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column
    @JsonIgnore
    private LocalDateTime updatedAt;
}
