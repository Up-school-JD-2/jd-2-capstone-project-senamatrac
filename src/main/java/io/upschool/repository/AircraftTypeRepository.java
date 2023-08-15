package io.upschool.repository;

import io.upschool.entity.AircraftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftTypeRepository extends JpaRepository<AircraftType, Long> {

    boolean existsByIataCode(String iataCode);
}
