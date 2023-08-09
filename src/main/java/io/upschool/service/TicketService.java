package io.upschool.service;

import io.upschool.dto.request.TicketRequest;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import io.upschool.exception.DataNotFoundException;
import io.upschool.mapper.entity.TicketMapper;
import io.upschool.repository.FlightRepository;
import io.upschool.repository.PassengerRepository;
import io.upschool.repository.PaymentRepository;
import io.upschool.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final PaymentRepository paymentRepository;

    public Ticket save(TicketRequest ticketRequest) throws DataNotFoundException {
        Flight flight = flightRepository.findById(ticketRequest.getFlightId()).orElseThrow(() -> new DataNotFoundException("flight id:" + ticketRequest.getFlightId()));


        Ticket ticket = ticketMapper.map(ticketRequest, flight);
        return ticket;
    }
}
