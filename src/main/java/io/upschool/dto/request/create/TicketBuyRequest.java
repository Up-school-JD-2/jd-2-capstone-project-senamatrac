package io.upschool.dto.request.create;

import io.upschool.enums.SeatType;
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
public class TicketBuyRequest {
    @NotBlank
    private String ticketNumber;

    @NotNull
    private Long flightId;

    @NotNull
    private SeatType seatType;

    @Valid
    @NotNull
    private PassengerCreateRequest passenger;

    @Valid
    @NotNull
    private PaymentCreateRequest payment;

}
