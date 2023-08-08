package io.upschool.dto.request;

import io.upschool.enums.LegType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightRequest {

    @NotBlank
    private String flightNumber;
    @NotNull
    private LocalDate flightDate;
    @NotNull
    private Long airlineId;
    @NotNull
    private LegType legType;
    @NotNull
    private Long routeId;
    @Valid
    @NotEmpty
    private Set<FlightSeatPriceRequest> flightSeatPrice;
}
