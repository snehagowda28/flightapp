package com.flightapp.exceptions;

import com.flightapp.openapi.dto.Error;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class FlightApiException {

    private final String SOURCE = "FLIGHT_APP";

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Error> handleNotFoundException(Exception ex, WebRequest request) {
        return ResponseEntity.ok(Error.builder()
                .source(SOURCE)
                .description(ex.getLocalizedMessage())
                .reasonCode("NOT_FOUND")
                .build());
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Error> handleEntityNotFoundException(Exception ex, WebRequest request) {
        return ResponseEntity.ok(Error.builder()
                .source(SOURCE)
                .description(ex.getLocalizedMessage())
                .reasonCode("ENTITY_NOT_FOUND")
                .build());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Error> handleConstraintViolation(Exception ex, WebRequest request) {
        return ResponseEntity.ok(Error.builder()
                .source(SOURCE)
                .description(ex.getLocalizedMessage())
                .reasonCode("CONSTRAINT_VIOLATION")
                .details("FLIGHT ID NOT FOUND")
                .build());
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Error> handleException(Exception ex, WebRequest request) {
        return ResponseEntity.ok(Error.builder()
                .source(SOURCE)
                .description(ex.getLocalizedMessage())
                .reasonCode("FLIGHT_APP_ERROR")
                .build());
    }
}
