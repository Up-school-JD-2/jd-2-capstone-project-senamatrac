package io.upschool.repository;

import io.upschool.entity.FlightSeatPrice;
import io.upschool.enums.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightSeatPriceRepository extends JpaRepository<FlightSeatPrice, Long> {
    FlightSeatPrice findByFlightIdAndSeatType(Long flightId, SeatType seatType);

    List<FlightSeatPrice> findByFlightId(Long flightId);
}