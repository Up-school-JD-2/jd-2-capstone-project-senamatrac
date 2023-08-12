package io.upschool.mapper.entity;

import io.upschool.dto.request.TicketBuyRequest;
import io.upschool.entity.Flight;
import io.upschool.entity.Passenger;
import io.upschool.entity.Payment;
import io.upschool.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FlightMapper.class, PassengerMapper.class, PaymentMapper.class})
public interface TicketMapper {

    Ticket map(TicketBuyRequest ticket);


    @Mapping(target = ".", source = "ticketBuyRequest.ticketNumber")
    @Mapping(target = "flight", source = "flight")
    @Mapping(target = "payment", source = "payment")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    Ticket map(TicketBuyRequest ticketBuyRequest, Flight flight, Payment payment);

    @Mapping(target = "ticketNumber", source = "ticketBuyRequest.ticketNumber")
    @Mapping(target = "seatType", source = "ticketBuyRequest.seatType")
    @Mapping(target = "flight", source = "flight")
    @Mapping(target = "payment", source = "payment")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "passenger", source = "passenger")
    @Mapping(target = "id", ignore = true)
    Ticket map(TicketBuyRequest ticketBuyRequest, Flight flight, Payment payment, Passenger passenger);

}
