package io.upschool.repository;

import io.upschool.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
    @Override
    List<Airline> findAll();

    boolean existsByIataCode(String iataCode);
}
