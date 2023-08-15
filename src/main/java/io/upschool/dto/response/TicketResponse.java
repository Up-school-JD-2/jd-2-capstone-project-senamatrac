package io.upschool.dto.response;

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
public class TicketResponse {
    private Long id;
    private String ticketNumber;
    private SeatType seatType;
    private TicketStatus status;
    private FlightResponse flight;
    private PassengerResponse passenger;
    private PaymentResponse payment;
}
