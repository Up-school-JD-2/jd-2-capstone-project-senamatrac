package io.upschool.service;

import io.upschool.dto.request.CreditCardPaymentRequest;
import io.upschool.dto.request.PaymentRequest;
import io.upschool.dto.request.TicketBuyRequest;
import io.upschool.entity.*;
import io.upschool.enums.PaymentMethod;
import io.upschool.enums.PaymentStatus;
import io.upschool.enums.TicketStatus;
import io.upschool.exception.*;
import io.upschool.mapper.entity.PaymentMapper;
import io.upschool.mapper.entity.PassengerMapper;
import io.upschool.mapper.entity.TicketMapper;
import io.upschool.repository.FlightRepository;
import io.upschool.repository.FlightSeatPriceRepository;
import io.upschool.repository.PassengerRepository;
import io.upschool.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final FlightRepository flightRepository;
    private final FlightSeatPriceRepository flightSeatPriceRepository;
    private final PaymentMapper paymentMapper;
    @Value("${capstone.ticket.taxPercentage}")
    private Double taxPercentage;
    private final PassengerRepository passengerRepository;

    @Transactional
    public Ticket buy(TicketBuyRequest ticketBuyRequest) throws DataNotFoundException, DuplicateEntryException, TicketSoldOut, UnsupportedPaymentType {
        ServiceExceptionUtil.check(ticketRepository::existsByTicketNumber, ticketBuyRequest.getTicketNumber(), () -> new DuplicateEntryException("ticket number:" + ticketBuyRequest.getTicketNumber()));
        Flight flight = flightRepository.findById(ticketBuyRequest.getFlightId()).orElseThrow(() -> new DataNotFoundException("flight id:" + ticketBuyRequest.getFlightId()));
        checkCapacity(ticketBuyRequest, flight);

        FlightSeatPrice flightSeatPrice = flightSeatPriceRepository.findByFlightIdAndSeatType(ticketBuyRequest.getFlightId(), ticketBuyRequest.getSeatType());
        Payment payment = createPayment(ticketBuyRequest.getPaymentRequest(), flightSeatPrice);

        Optional<Passenger> passengerOptional = passengerRepository.findByIdentityNumber(ticketBuyRequest.getPassenger().getIdentityNumber());
        Ticket ticket = passengerOptional.map(passenger -> ticketMapper.map(ticketBuyRequest, flight, payment, passenger))
                .orElse(ticketMapper.map(ticketBuyRequest, flight, payment));
        ticket.setStatus(TicketStatus.BOOKED);

        return ticketRepository.save(ticket);
    }

    private Payment createPayment(PaymentRequest paymentRequest, FlightSeatPrice flightSeatPrice) throws UnsupportedPaymentType {
        var subTotal = flightSeatPrice.getPrice();
        var taxFee = subTotal.multiply(BigDecimal.valueOf((taxPercentage / 100)));
        var total = subTotal.add(taxFee);
        if (paymentRequest.getPaymentMethod() == PaymentMethod.CREDIT_CARD){
            CreditCardPaymentRequest creditCardPaymentRequest = (CreditCardPaymentRequest) paymentRequest;
            return paymentMapper.map(creditCardPaymentRequest,taxFee,total);
        }else{
            throw new UnsupportedPaymentType("Unsupported payment method.");
        }
    }

    private void checkCapacity(TicketBuyRequest ticketBuyRequest, Flight flight) throws TicketSoldOut {
        long seatTypeCapacity = flight.getAircraftType().getSeats().stream().filter(seat -> seat.getSeatType() == ticketBuyRequest.getSeatType()).count();
        long soldTicketCount = ticketRepository.countByFlight_IdAndSeatTypeAndStatus(ticketBuyRequest.getFlightId(), ticketBuyRequest.getSeatType(), TicketStatus.BOOKED);

        if (seatTypeCapacity <= soldTicketCount) {
            throw new TicketSoldOut("Flight " + flight.getFlightNumber() + " belongs to all " + ticketBuyRequest.getSeatType() + " class tickets sold out.");
        }
    }

    public Page<Ticket> findAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findById(Long id) throws DataNotFoundException {
        return ticketRepository.findById(id).orElseThrow(() -> new DataNotFoundException("ticket id:" + id));
    }

    public Page<Ticket> findAllByFlightId(Long id, Pageable pageable) {
        return ticketRepository.findAllByFlight_Id(id, pageable);
    }

    public Ticket cancel(Long id) throws DataNotFoundException {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new DataNotFoundException("ticket id: " + id));
        ticket.setStatus(TicketStatus.CANCELED);
        //TODO çek

        ticket.getPayment().setStatus(PaymentStatus.REFUNDED);
        return ticketRepository.save(ticket);
    }
}
