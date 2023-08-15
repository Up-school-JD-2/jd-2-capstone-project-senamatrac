package io.upschool.dto.request.search;

import io.upschool.enums.SeatType;
import io.upschool.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSearchRequest {
    private Long id;
    private String ticketNumber;
    private SeatType seatType;
    private TicketStatus status;
    private FlightSearchRequest flight;
    private PassengerSearchRequest passenger;
    private PaymentSearchRequest payment;
}

