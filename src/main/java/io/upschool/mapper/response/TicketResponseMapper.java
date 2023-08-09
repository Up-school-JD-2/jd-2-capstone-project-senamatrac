package io.upschool.mapper.response;

import io.upschool.dto.response.TicketResponse;
import io.upschool.entity.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FlightResponseMapper.class, PassengerResponseMapper.class, PaymentResponseMapper.class})
public interface TicketResponseMapper {

    TicketResponse map(Ticket ticket);
}
