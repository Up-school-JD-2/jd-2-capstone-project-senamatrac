package io.upschool.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private Long flightId;

    @Valid
    @NotNull
    private PassengerRequest passenger;

    @Valid
    @NotNull
    private PaymentRequest payment;
}
