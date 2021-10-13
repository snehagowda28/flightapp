package com.flightapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum FlightCompany {
    AIRASIA, EMIRATES, INDIGO
}
