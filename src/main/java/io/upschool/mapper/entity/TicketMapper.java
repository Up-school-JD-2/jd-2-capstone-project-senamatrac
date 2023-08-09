package io.upschool.mapper.entity;

import io.upschool.dto.request.TicketRequest;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FlightMapper.class, PassengerMapper.class, PaymentMapper.class})
public interface TicketMapper {

    Ticket map(TicketRequest ticket);


    @Mapping(target = "flight", source = "flight")
    @Mapping(target = "passenger", source = "ticketRequest.passenger")
    @Mapping(target = "payment", source = "ticketRequest.payment")
    @Mapping(target = "ticketNumber", source = "ticketRequest.ticketNumber")
    @Mapping(target = "id", ignore = true)
    Ticket map(TicketRequest ticketRequest, Flight flight);
}
