package io.upschool.dto.response;

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
    private FlightResponse flight;
    private PassengerResponse passenger;
    private PaymentResponse payment;
}
