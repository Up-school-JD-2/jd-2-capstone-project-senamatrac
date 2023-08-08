package io.upschool.dto.request;

import io.upschool.entity.Flight;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketRequest {
    @NotBlank
    private String ticketNumber;

    @NotBlank
    private Long flightId;

    @Valid
    private PassengerRequest passenger;

    @Valid
    private PaymentRequest payment;
}
