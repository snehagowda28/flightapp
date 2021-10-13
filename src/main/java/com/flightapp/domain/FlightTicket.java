package com.flightapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightTicket {

    @Id
    @Column(name = "PNR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int pnr;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PNR", referencedColumnName = "PNR")
    private Flight flight;

    @Column
    @JsonIgnore
    private LocalDate createdAt;

    @Column
    @JsonIgnore
    private LocalDate updatedAt;
}
