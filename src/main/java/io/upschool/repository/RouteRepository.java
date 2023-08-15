package io.upschool.repository;

import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.enums.RouteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    boolean existsByOriginAndDestination(Airport origin, Airport destination);

    boolean existsByOrigin_IdAndDestination_Id(Long originId, Long destinationId);

    Optional<Route> findByIdAndStatus(Long id, RouteStatus status);
}
