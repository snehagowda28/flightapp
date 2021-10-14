package com.flightapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightapp.openapi.dto.FlightBookingPassengers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String pnr;

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
    @ToString.Exclude
    @OneToMany(mappedBy = "flightBooking", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passenger> passengers;

    @Column
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column
    @JsonIgnore
    private LocalDateTime updatedAt;
}
