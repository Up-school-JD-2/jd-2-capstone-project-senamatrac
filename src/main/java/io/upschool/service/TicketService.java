package io.upschool.service;

import io.upschool.dto.request.TicketRequest;
import io.upschool.entity.Flight;
import io.upschool.entity.FlightSeatPrice;
import io.upschool.entity.Ticket;
import io.upschool.exception.DataNotFoundException;
import io.upschool.mapper.entity.TicketMapper;
import io.upschool.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final PaymentRepository paymentRepository;
    private final FlightSeatPriceRepository flightSeatPriceRepository;

    @Transactional
    public Ticket save(TicketRequest ticketRequest) throws DataNotFoundException {
        Flight flight = flightRepository.findById(ticketRequest.getFlightId()).orElseThrow(() -> new DataNotFoundException("flight id:" + ticketRequest.getFlightId()));
        FlightSeatPrice flightSeatPrice = flightSeatPriceRepository.findByFlightIdAndSeatType(ticketRequest.getFlightId(),ticketRequest.getSeatType());
        Ticket ticket = ticketMapper.map(ticketRequest, flight);
        return ticketRepository.save(ticket);
    }
}
