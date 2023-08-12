package io.upschool.repository;

import io.upschool.entity.Ticket;
import io.upschool.enums.SeatType;
import io.upschool.enums.TicketStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    boolean existsByTicketNumber(String ticketNumber);

    List<Ticket> findByFlight_Id(Long flightId);

    Page<Ticket> findAllByFlight_Id(Long id, Pageable pageable);

    long countByFlight_IdAndSeatTypeAndStatus(Long flightId, SeatType seatType, TicketStatus ticketStatus);
}
