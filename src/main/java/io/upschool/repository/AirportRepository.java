package io.upschool.repository;

import io.upschool.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Long> {
    Optional<Airport> findByCity_Id(Long city);
    boolean existsAirportByCity_Id(Long city);

    Optional<Airport> findByIataCode(String iataCode);

    boolean existsAirportByIataCode(String s);
}
