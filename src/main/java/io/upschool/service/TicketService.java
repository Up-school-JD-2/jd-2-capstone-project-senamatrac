package io.upschool.service;

import io.upschool.dto.request.PassengerRequest;
import io.upschool.dto.request.TicketRequest;
import io.upschool.entity.*;
import io.upschool.enums.PaymentType;
import io.upschool.enums.TicketStatus;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.CreditCardMapper;
import io.upschool.mapper.entity.TicketMapper;
import io.upschool.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final FlightRepository flightRepository;
    private final FlightSeatPriceRepository flightSeatPriceRepository;
    private final CreditCardMapper creditCardMapper;
    @Value("${capstone.ticket.taxPercentage}")
    private Double taxPercentage ;
    private final PassengerRepository passengerRepository;

    @Transactional
    public Ticket save(TicketRequest ticketRequest) throws DataNotFoundException, DuplicateEntryException {
        ServiceExceptionUtil.check(ticketRepository::existsByTicketNumber,ticketRequest.getTicketNumber(),()->new DuplicateEntryException("ticket number:"+ticketRequest.getTicketNumber()));
        Flight flight = flightRepository.findById(ticketRequest.getFlightId()).orElseThrow(() -> new DataNotFoundException("flight id:" + ticketRequest.getFlightId()));
        FlightSeatPrice flightSeatPrice = flightSeatPriceRepository.findByFlightIdAndSeatType(ticketRequest.getFlightId(),ticketRequest.getSeatType());
        Optional<Passenger> optionalPassenger = passengerRepository.findByIdentityNumber(ticketRequest.getPassenger().getIdentityNumber());

        var subTotal = flightSeatPrice.getPrice().doubleValue();
        var taxFee = subTotal * (taxPercentage / 100);
        Payment payment = Payment.builder()
                .paymentType(PaymentType.CREDIT_CART)
                .tax(BigDecimal.valueOf(taxFee))
                .total(BigDecimal.valueOf(subTotal+taxFee))
                .creditCard(creditCardMapper.map(ticketRequest.getCreditCard()))
                .build();

        Ticket ticket;
        if (optionalPassenger.isPresent()){
            ticket =  ticketMapper.mapIgnorePassenger(ticketRequest, flight, payment);
            ticket.setPassenger(optionalPassenger.get());
        }else {
            ticket = ticketMapper.map(ticketRequest, flight, payment);
        }
        ticket.setStatus(TicketStatus.BOOKED);

        return ticketRepository.save(ticket);
    }
}
