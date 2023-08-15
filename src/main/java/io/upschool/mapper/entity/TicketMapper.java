package io.upschool.mapper.entity;

import io.upschool.dto.request.create.TicketBuyRequest;
import io.upschool.entity.Flight;
import io.upschool.entity.Passenger;
import io.upschool.entity.Payment;
import io.upschool.entity.Ticket;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FlightMapper.class, PassengerMapper.class, PaymentMapper.class}, builder = @Builder(disableBuilder = true))
public interface TicketMapper {

    @Mapping(target = ".", source = "ticketBuyRequest")
    @Mapping(target = "flight", source = "flight")
    @Mapping(target = "payment", source = "payment")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Ticket map(TicketBuyRequest ticketBuyRequest, Flight flight, Payment payment);


    @Mapping(target = ".", source = "ticketBuyRequest")
    @Mapping(target = "flight", source = "flight")
    @Mapping(target = "payment", source = "payment")
    @Mapping(target = "passenger", source = "passenger")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Ticket map(TicketBuyRequest ticketBuyRequest, Flight flight, Payment payment, Passenger passenger);

   /* @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Ticket map(TicketSearchRequest ticketSearchRequest);*/
}
