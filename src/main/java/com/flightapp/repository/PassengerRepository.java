package com.flightapp.repository;

import com.flightapp.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    @Query(value = "SELECT p FROM PASSENGER WHERE p.pnr = :pnr", nativeQuery = true)
    List<Passenger> findByPNR(@Param("pnr") String pnr);
}
