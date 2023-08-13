package io.upschool.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class FlightRequest {

    @NotBlank
    private String flightNumber;
    @NotNull
    private LocalDate flightDate;
    @NotNull
    private Long airlineId;
    @NotNull
    private Long aircraftTypeId;
    @NotNull
    private Long routeId;
    @Valid
    @NotEmpty
    private Set<FlightSeatPriceRequest> flightSeatPrices;
}
